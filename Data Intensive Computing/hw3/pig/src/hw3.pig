FILES = LOAD 'hdfs:///pigdata/' using PigStorage(',','-tagsource');

StocksTemp = FOREACH FILES GENERATE $0 AS Stock, $1 AS Date, $7 AS Adj_Close;

stocks = FILTER StocksTemp BY $1 != 'Date';

split_data = FOREACH stocks GENERATE Stock AS Stock, SUBSTRING(Date,0,7) AS YearMon, SUBSTRING(Date,8,10) AS Day, Adj_Close; 
/* DUMP split_data; */

grouped_data = GROUP split_data BY (Stock, YearMon);
/* DUMP grouped_data; */

MinMax = FOREACH grouped_data GENERATE FLATTEN(split_data.(Stock,YearMon,Day,Adj_Close)), MIN(split_data.Day) AS minCP, MAX(split_data.Day) AS maxCP;

filteredMinMax = FILTER MinMax BY ( minCP == Day OR maxCP == Day );

filteredMinMax1 = FOREACH filteredMinMax GENERATE Stock AS StockL, YearMon AS YearMonL,Day AS DayL, Adj_Close AS Adj_CloseL;

filteredMinMax2 = FOREACH filteredMinMax GENERATE Stock AS StockR, YearMon AS YearMonR,Day AS DayR, Adj_Close AS Adj_CloseR;

temp1 = JOIN filteredMinMax1 BY (YearMonL), filteredMinMax2 BY (YearMonR);

temp2 = FILTER temp1 BY ((DayL < DayR) AND (StockL == StockR)) ;

temp3 = FOREACH temp2 GENERATE StockL AS Stock, YearMonL AS YearMon, ((Adj_CloseR - Adj_CloseL) / Adj_CloseL) AS Xi;

temp4 = GROUP temp3 BY Stock;

temp5 = FOREACH temp4 GENERATE FLATTEN(temp3.Stock) AS StockName, AVG(temp3.Xi) as xbar , COUNT(temp3.Xi) AS N ;

temp6 = DISTINCT temp5;

temp7 = JOIN temp3 BY Stock, temp6 BY StockName;

temp8 = FOREACH temp7 GENERATE Stock, Xi , xbar, N;

temp9 = FOREACH temp8 GENERATE Stock, ((Xi - xbar) * (Xi - xbar)) AS x, N;

temp10 = GROUP temp9 BY Stock;

temp11 = FOREACH temp10 GENERATE FLATTEN(temp9.Stock) AS StockName, SUM(temp9.x) AS x, FLATTEN(temp9.N) AS N;

temp12 = DISTINCT temp11;

temp13 = FOREACH temp12 GENERATE StockName, SQRT(x / ( N - 1 )) AS Volatility ;

temp14a = FILTER temp13 BY Volatility is not null;

temp14b = FILTER temp14a BY Volatility != 0.0 ;

temp15 = ORDER temp14b BY Volatility asc;
temp16 = LIMIT temp15 10 ;

temp17 = ORDER temp14b BY Volatility desc;
temp18 = LIMIT temp17 10 ;

temp19 = UNION temp16,temp18;
/* DUMP temp19; */

STORE temp19 INTO 'hdfs:///pigdata/wc_out';
