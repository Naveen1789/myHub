function[ERMS] = test_cfs(W, M, Lambda, S)

DataSet = dlmread('project1_data.mat',' ');

TestingTarget = DataSet(62662:69623,1);
TestingData = DataSet(62662:69623,2:47);

% Create Design Matrix
%---------------------------------------------------------------------
DesignMat = ones(6962,1);

for i = 2:M,

	% Generate 200 unique random numbers between 1 and 55699
	ran = randperm(length(TestingData)) ;

	Sample1 = TestingData(ran(1),:);

	% Fetch 200 random rows
	for i1 = 2:200,
		Sample1 = [Sample1; TestingData(ran(i1),:)];
	end;

	% Find Mean for each column
	Mu = mean(Sample1,1);
 
	phi_i = zeros(6962,1);

	for k = 1:6962,
		temp = TestingData(k,:) - Mu;
		phi_k_i = exp(-(temp * temp')/(2 * S * S)) ;

		phi_i(k,1) = phi_k_i;
	end ;

	DesignMat = [DesignMat phi_i];

end;

%---------------------------------------------------------------------


Err = ( TestingTarget - ( DesignMat * W ) ) ;
Ed = (0.5 * (Err' * Err));

Ew = 0;

for i2 = 1:length(W),
	Ew = Ew + ((W(i2))^2) ;
end;

Ew = Ew / 2;

E = Ed + ( Lambda * Ew );

% calculate ERMS
ERMS = sqrt(( 2 * E ) / length(TestingTarget) );


%------------------------

end