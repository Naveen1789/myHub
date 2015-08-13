function[ERMS_T ] = test_gd(WOpt, MOpt, LambdaOpt, S)

DataSet = dlmread('project1_data.mat',' ');

%-----------------------------Creating Design Matrix--------------------------------------

DesignMat = ones(69623,1);

% Creating Design Matrix
for i = 2:46,

	% Generate 200 unique random numbers between 1 and 55699
	ran = randperm(69623) ;

	Sample1 = DataSet(ran(1),:);

	% Fetch 200 random rows
	for i1 = 2:200,
		Sample1 = [Sample1; DataSet(ran(i1),:)];
	end;

	% Find Mean for each column
	Mu = mean(Sample1,1);

	phi_i = zeros(69623,1);

	for k = 1:69623,
		temp = DataSet(k,:) - Mu;
		phi_k_i = exp(-(temp * temp')/(2 * S * S)) ;

		phi_i(k,1) = phi_k_i;
	end ;

	DesignMat = [DesignMat phi_i];

end;

%-----------------------------End of Creating Design Matrix----------------------------------

%----------------------------------------Testing---------------------------------------------

TestingTarget = DataSet(62662:69623,1);
TestingData = DataSet(62662:69623,2:47);



Err = ( TestingTarget - ( DesignMat(62662:69623,1:MOpt) * WOpt ) ) ;
Ed = (0.5 * (Err' * Err));

Ew = 0;

for i2 = 1:length(WOpt),
	Ew = Ew + ((WOpt(i2))^2) ;
end;

Ew = Ew / 2;

E = Ed + ( LambdaOpt * Ew );

% calculate ERMS
ERMS_T = sqrt(( 2 * E ) / length(TestingTarget) );



%------------------------------------end of Testing----------------------------------------