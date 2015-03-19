# =====================================================================
# CSE587
# Author: Naveen Narayan
# Email: nnarayan@buffalo.edu
# =====================================================================

# need to install (at least) the following two packages in CCR
# install.packages("forecast")
# install.packages("fpp")
# data path /gpfs/courses/cse587/spring2015/data/hw2/data

library(forecast)
library(fpp)

ArimaMat = matrix(nrow=3000,ncol=2)
HoltWintersMat = matrix(nrow=3000,ncol=2)
LinearRegressionMat = matrix(nrow=3000,ncol=2)

# need to read the stocklist, and loop all files

directory <- "/gpfs/courses/cse587/spring2015/data/hw2/data"
listOfFiles <- list.files(directory, pattern="*.csv")

index = 1

for (counter in 1:length(listOfFiles) ) {

	# read one file
	filename = paste(directory, listOfFiles[counter], sep="/")

	# if file is not empty
	if(file.info(filename)[1]>0) {
	  
		  # read one csv file into variable (DO NOT EDIT)
		  textData=read.csv(file=filename, header=T)
		  
		  if(nrow(textData) != 754){
			next
		  }

		  # convert txt data to time-series data, in day unit (DO NOT EDIT)
		  tsData = ts(rev(textData$Adj.Close),start=c(2012, 1),frequency=365)

		  # define train data (DO NOT EDIT)
		  trainData = window(tsData, end=c(2014,14))
		  
		  # define test data (DO NOT EDIT)
		  testData = window(tsData, start=c(2014,15))
			     
		  # MAE row vectors (DO NOT EDIT)
		  # MAE - Mean Absolute Error
		  MAEA = matrix(NA,1,length(testData))
		  MAEH = matrix(NA,1,length(testData))
		  MAEL = matrix(NA,1,length(testData))

		  
		  # apply Arima model (DO NOT EDIT)
		  fitDataForArima = auto.arima(trainData,seasonal=FALSE,lambda=NULL,approximation=TRUE)

		  # apply Holt-Winters model (DO NOT EDIT)
		  fitDataForHoltWinters = HoltWinters(trainData,gamma=FALSE)

		  # apply Linear Regression model (DO NOT EDIT)
		  fitDataForLinearRegression = tslm(trainData ~ trend + season)
		  	  
		  # apply forecast(DO NOT EDIT)
		  forecastDataForArima = forecast(fitDataForArima, h=length(testData))
		  forecastDataForHoltWinters = forecast(fitDataForHoltWinters, h=length(testData))
		  forecastDataForLinearRegression = forecast(fitDataForLinearRegression, h=length(testData))

		  
		  # print variable and see what is in the result data set
		  # print(forecastData)
		  
		  # calculate Mean Absolute Error for Arima
		  for(i in 1:length(testData))
		  {
		    MAEA[1,i] = abs(forecastDataForArima$mean[i] - testData[i])
		  }

		  # calculate Mean Absolute Error for Holt-Winters
		  for(i in 1:length(testData))
		  {
		    MAEH[1,i] = abs(forecastDataForHoltWinters$mean[i] - testData[i])
		  }

		  # calculate Mean Absolute Error for Linear Regression
		  for(i in 1:length(testData))
		  {
		    MAEL[1,i] = abs(forecastDataForLinearRegression$mean[i] - testData[i])
		  }

		  # this is the result you need for stock AAPL

		  # Stock Name
		  ArimaMat[index,1] = substr(listOfFiles[counter], 1, nchar(listOfFiles[counter])-4)
		  ArimaMat[index,2] = sum(MAEA[1,1:10])
		  HoltWintersMat[index,1] = substr(listOfFiles[counter], 1, nchar(listOfFiles[counter])-4)
		  HoltWintersMat[index,2] = sum(MAEH[1,1:10])
		  LinearRegressionMat[index,1] = substr(listOfFiles[counter], 1, nchar(listOfFiles[counter])-4)
		  LinearRegressionMat[index,2] = sum(MAEL[1,1:10])

		  index = index + 1

	  }

  }
  
ArimaMat = ArimaMat[sort.list(ArimaMat[,2]), ]
HoltWintersMat = HoltWintersMat[sort.list(HoltWintersMat[,2]), ]
LinearRegressionMat = LinearRegressionMat[sort.list(LinearRegressionMat[,2]), ]

print("Top stocks - Arima Model")
for (i in 1:10){
	print(paste(ArimaMat[i,1],ArimaMat[i,2],sep=":"))
}
print("Top stocks - HoltWinters Model")
for (i in 1:10){
	print(paste(HoltWintersMat[i,1],HoltWintersMat[i,2],sep=":"))
}
print("Top stocks - LinearRegression Model")
for (i in 1:10){
	print(paste(LinearRegressionMat[i,1],LinearRegressionMat[i,2],sep=":"))
}


jpeg('arima.jpg')  
  # plot the top 10 minimum sum of MAE in Arima model
  plot(ArimaMat[1:10,2], col = "blue")
  lines(ArimaMat[1:10,2], lw = 2, col = "red")
dev.off()

jpeg('hw.jpg')  
  # plot the top 10 minimum sum of MAE in Holt-Winters model
  plot(HoltWintersMat[1:10,2], col = "blue")
  lines(HoltWintersMat[1:10,2], lw = 2, col = "green")
dev.off()

jpeg('lm.jpg')  
  # plot the top 10 minimum sum of MAE in Linear Regression model
  plot(LinearRegressionMat[1:10,2], col = "blue")
  lines(LinearRegressionMat[1:10,2], lw = 2, col = "yellow")
dev.off()



#jpeg('Combined.jpg')  
#  # plot the top 10 minimum sum of MAE in 3 models
#
#  plot(ArimaMat[1:10], col = "black")
#  lines(ArimaMat[1:10], lw = 2, col = "red")
#
#  plot(HoltWintersMat[1:10], col = "black")
#  lines(HoltWintersMat[1:10], lw = 2, col = "green")
#
#  plot(LinearRegressionMat[1:10], col = "black")
#  lines(LinearRegressionMat[1:10], lw = 2, col = "blue")
#dev.off()