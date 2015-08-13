function[MOptimal1, LambdaOptimal1] = train_cfs()

DataSet = dlmread('project1_data.mat',' ');

AcceptableERMS = 0.56 ;
OptERMS = 999 ;

% Fetch training data

TrainingTarget = DataSet(1:55699,1);
TrainingData = DataSet(1:55699,2:47);

%--------------------------------------------for each M---------------------------------------------------------
MVec = [ 14 ];
LambdaX = ones(1,length(MVec));
for p = 1:length(MVec),
	OptERMS = 999 ;
	M = MVec(p);
	%--------------------------------------------for each Lambda---------------------------------------------
	LambdaVec = [ 0.1, 1, 10];
	for q = 1:length(LambdaVec),
	Lambda = LambdaVec(q);
		%---------------------------------------for each S------------------------------------------------
		
		SVec = [ 0.7 ];
		for r = 1:length(SVec),
			S = SVec(r);	
			%-----------------------------Creating Design Matrix--------------------------------------
			DesignMat = ones(55699,1);

			for i = 2:M,
				% Create mean vector

				% Generate 200 unique random numbers between 1 and 55699
				ran = randperm(55699) ;

				Sample1 = TrainingData(ran(1),:);

				% Fetch remaining 199 random rows
				for i1 = 2:200,
					Sample1 = [Sample1; TrainingData(ran(i1),:)];
				end;

				% Find Mean for each column
				Mu = mean(Sample1,1);

				% 
				phi_i = zeros(55699,1);

				for k = 1:55699,
					temp = TrainingData(k,:) - Mu;
					phi_k_i = exp(-(temp * temp')/(2 * S * S)) ;
					phi_i(k,1) = phi_k_i;
				end ;

				DesignMat = [DesignMat phi_i];

			end;
			%-----------------------------end of creating Design Matrix--------------------------------------

			LambdaI = Lambda * eye(M);

			W = pinv( ( DesignMat' * DesignMat ) + LambdaI ) * DesignMat' * TrainingTarget;

			Err = ( TrainingTarget - ( DesignMat * W ) ) ;
			Ed = (0.5 * (Err' * Err));

			Ew = 0;
			for i2 = 1:length(W),
				Ew = Ew + ((W(i2))^2) ;
			end;

			Ew = Ew / 2;

			E = Ed + ( Lambda * Ew );

			% calculate ERMS
			ERMS = sqrt(( 2 * E ) / 55699 );

			%-----------------------------------if loop---------------------------------------------
			% ----Store the least ERMS----
			if ( ( ERMS < AcceptableERMS ) && ( ERMS < OptERMS ) ),
				OptERMS = ERMS ;
				WOptimal = W;
				SOptimal = S;
				LambdaOptimal = Lambda;
				MuOptimal  = Mu;
				MOptimal = M;
				DesignMatrixOptimal = DesignMat;

				% validate for the training data set
				%----------------------------VALIDATION------------------------------------------

				ValidationTarget = DataSet(55700:62661,1);
				ValidationData = DataSet(55700:62661,2:47);

				DesignMat1 = ones(6962,1);

				for iNew = 2:MOptimal,

					ran1 = randperm(6962) ;

					Sample2 = ValidationData(ran1(1),:);

					for iNew1 = 2:200,
						Sample2 = [Sample2; ValidationData(ran1(iNew1),:)];
					end;

					Mu1 = mean(Sample2,1);
				 
					phi_i1 = zeros(6962,1);

					for k1 = 1:6962,
						temp1 = ValidationData(k1,:) - Mu1;
						phi_k1_i1 = exp(-(temp1 * temp1')/(2 * S * S)) ;

						phi_i1(k1,1) = phi_k1_i1;
					end ;

					DesignMat1 = [DesignMat1 phi_i1];

				end;

				ErrV = ( ValidationTarget - ( DesignMat1 * WOptimal ) ) ;
				EdV = (0.5 * (ErrV' * ErrV));

				EwV = 0;
				for i2 = 1:length(WOptimal),
					EwV = EwV + ((WOptimal(i2))^2) ;
				end;

				EwV = EwV / 2;

				EV = EdV + ( LambdaOptimal * EwV );

				% calculate ERMS
				ERMSV = sqrt(( 2 * EV ) / length(ValidationTarget) );
				if(abs(ERMS-ERMSV) < 50),

					WOptimal1 = WOptimal ;
					MuOptimal1 = MuOptimal;
					SOptimal1 = SOptimal;
					MOptimal1 = MOptimal;
					LambdaOptimal1 = LambdaOptimal;
					LambdaX(p) = LambdaOptimal1;


					break;


				end;

				%----------------------END OF VALIDATION------------------------------------------
			end;
			%-----------------------------------end of if loop----------------------------------------
		end;
		%---------------------------------------for each S------------------------------------------------

	end;
	%-------------------------------------------for each Lambda-----------------------------------------------






end;



%--------------------------------------------for each M-----------------------------------------------------------
save W_cfs WOptimal1 ;
save mu_cfs MuOptimal1;
save s_cfs SOptimal1 ;


end

