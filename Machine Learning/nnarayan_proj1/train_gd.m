function[MOpt, LambdaOpt, WOptGD ] = train_gd(S, M )

DataSet = dlmread('project1_data.mat',' ');

LambdaVec = [ 0.01, 1, 100 ];

MOpt = 999 ;
LambdaOpt = 0 ;
ERMSOpt = 999 ;

% Fetch training data

TrainingTarget = DataSet(1:55699,1);
TrainingData = DataSet(1:55699,2:47);

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
	MuGD = mean(Sample1,1);

	phi_i = zeros(69623,1);

	for k = 1:69623,
		temp = DataSet(k,:) - MuGD;
		phi_k_i = exp(-(temp * temp')/(2 * S * S)) ;

		phi_i(k,1) = phi_k_i;
	end ;

	DesignMat = [DesignMat phi_i];

end;

%-----------------------------End of Creating Design Matrix----------------------------------


%-----------------------------------------M--------------------------------------------------

for temp2 = 10:M,

M_ERMS = 999 ;
M_Lambda = 0;
%-----------------------------------------Lambda---------------------------------------------

	for iNew = 1: length(LambdaVec);,

		Lambda = LambdaVec(iNew);

		W = zeros((temp2),1);

		eta = 1 ;

		lim = 0.00001 ;

		ErrPrev = 999;

		for temp3 = 1:55699,

			W = W + ((eta * ( TrainingTarget(temp3) - (DesignMat(temp3,1:temp2) * W))) *  DesignMat(temp3,1:temp2)' ) ;

			Err = ( TrainingTarget - ( DesignMat(1:55699,1:temp2) * W ) ) ;

			Ed = (0.5 * (Err' * Err));

			Ew = 0;

			for i2 = 1:length(W),
				Ew = Ew + ((W(i2))^2) ;
			end;

			Ew = Ew / 2;

			E = Ed + ( Lambda * Ew );

			% calculate ERMS
			ERMS = sqrt(( 2 * E ) / length(TrainingTarget) );



			if( ERMS > ErrPrev),
				eta = eta / 2 ;
			end;
			if (abs(ERMS - ErrPrev) < lim),

			break ;
			end;

			ErrPrev = ERMS ;



		end;

	% Check if Lambda is optimal

	if ( ERMS < M_ERMS ),
		M_ERMS = ERMS;
		M_Lambda = Lambda;
	end;

	% Check for global optima

	if( ERMS < ERMSOpt ),
		ERMSOpt = ERMS;
		LambdaOpt = Lambda ;
		MOpt = temp2 ;
		WOptGD = W;
	end;

	end;
%-----------------------------------------End of Lambda--------------------------------------


end;
%-----------------------------------------End of M-------------------------------------------

%----------------------------------------validation------------------------------------------

ValidationData = DataSet(55700:62661,2:47);
ValidationTarget = DataSet(55700:62661,1);

Err = ( ValidationTarget - ( DesignMat(55700:62661,1:MOpt) * WOptGD ) ) ;
Ed = (0.5 * (Err' * Err));

Ew = 0;

for i2 = 1:length(WOptGD),
	Ew = Ew + ((WOptGD(i2))^2) ;
end;

Ew = Ew / 2;

E = Ed + ( LambdaOpt * Ew );

% calculate ERMS
ERMS_V = sqrt(( 2 * E ) / length(ValidationTarget) );



%------------------------------------end of validation----------------------------------------

save W_gd WOptGD ;
save mu_gd MuGD;
SOptimal1GD = S ;
save s_gd SOptimal1GD ;

end