load('InputTrain.mat');
load('TargetTrain.mat');

% ######################################## Bias ########################################

% Bias : Add 1 to each row

biasInput = ones( size(InputTrain,1) , 1 );

InputTrain = [ biasInput InputTrain ];

% ######################################## Bias ########################################

% ##################################### Initialize #####################################

numOfUnitsInHiddenLayer = 60 ;

eta = 0.1 ;

W1 = -1 + (2) * rand ( size (InputTrain,2), numOfUnitsInHiddenLayer );
DELTAA = zeros ( size (InputTrain,2), numOfUnitsInHiddenLayer );

W2 = -1 + (2) * rand ( ( numOfUnitsInHiddenLayer + 1 ), 10 );
DELTAB = zeros ( ( numOfUnitsInHiddenLayer + 1 ), 10 ) ;



ranPerm = randperm(size(InputTrain, 1));

% ##################################### Initialize #####################################

for iter = 1 : 1000,

	ranPerm = randperm(size(InputTrain, 1));

	deltaH = zeros(513, numOfUnitsInHiddenLayer);
	deltaO = zeros(( numOfUnitsInHiddenLayer + 1), 10);

	numOfSamples = size (InputTrain,1) ;
	% numOfSamples = 1000;

	  for row = 1 : numOfSamples,

		% Forward propogation

		% Pick a row at random
		ranRow = ranPerm(1,row) ;

		a1 = InputTrain(ranRow,:) ;

		% Input layer to hidden layer
		z2 = a1 * W1 ;

		% activation unit
		a2Temp = sigmf( z2, [ 1,0 ]);

		% Add bias
		a2 = [ 1 a2Temp ];

		% Hidden layer to output layer
		z3 = a2 * W2 ;
		
		% activation
		y = sigmf( z3, [ 1,0 ]) ;

		% Back propogation

		delta3 = y - TargetTrain(ranRow,:);

		onesTemp = ones(size(a2));
		delta2 = (delta3 * W2') .* (a2 .* (onesTemp - a2));

		deltaO = deltaO + ( a2' * delta3 );

		deltaHTemp = a1' * delta2 ;

		deltaH = deltaH + ( deltaHTemp(:,2:(numOfUnitsInHiddenLayer+1)) );

		end ;

		W1 = W1 - ( eta * ( deltaH / numOfSamples )) ;
		W2 = W2 - ( eta * ( deltaO / numOfSamples ));
end ;

