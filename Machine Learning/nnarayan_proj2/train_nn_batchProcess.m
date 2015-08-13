% ##################################### Load data #####################################

load('InputTrain.mat');
load('TargetTrain.mat');

% ######################################## Bias ########################################

% Bias : Add 1 to each row

biasInput = ones( size(InputTrain,1) , 1 );

InputTrain = [ biasInput InputTrain ];

size( InputTrain, 1)

% ##################################### Initialize #####################################

numOfUnitsInHiddenLayer = 60 ;

% W1 = ones ( numOfUnitsInHiddenLayer, size (InputTrain,2) );
W1 = -1 + (2) * rand ( numOfUnitsInHiddenLayer, size (InputTrain,2));

% W2 = ones ( 10, (numOfUnitsInHiddenLayer + 1) );
W2 = -1 + (2) * rand ( 10, (numOfUnitsInHiddenLayer + 1));

deltah = zeros((numOfUnitsInHiddenLayer + 1), 513);
deltao = zeros(10,(numOfUnitsInHiddenLayer + 1));

% ################################### Randomize ########################################

Input = InputTrain ;

Target = TargetTrain ;

kk = randperm(size(InputTrain, 1));

% Randomize Input vector

for i = 1 : size(kk,2),
	k = kk(1,i);
	Input(i,:) = InputTrain(k,:);
end ;

% Randomize Target vector

for i = 1 : size(kk,2),
	k = kk(1,i);
	Target(i,:) = TargetTrain(k,:);
end ;

% ################################### Randomize ########################################

for iter = 1 : 1000,
% ################################### Fwd propogation ##################################

% ################################ I/p to hidden layer #################################

a2 = sigmf( Input * W1' , [1 0]) ;

% ################################## Add bias to a2 ####################################

biasHidden = ones( size(a2,1) , 1 );

a2 = [ biasHidden a2 ] ;

% ############################# hidden layer to o/p layer ##############################

y = sigmf( a2 * W2' , [1 0]) ;

% ################################### Fwd propogation ##################################

% ################################### Back propogation #################################

delta3 = y - Target;

onesTemp = ones (size(a2)) ;

delta2 = ( delta3 * W2 ) .* ( a2 .* (onesTemp - a2) );

Delta2 = ( delta3' * a2 ) / size (Input,1) ;

Delta1 = ( (delta2(:,2:size(delta2,2)))' * Input ) / size (Input,1) ;

eta1 = 0.15 ;
eta2 = 0.15 ;

Wtemp1 = W1 - eta1 * Delta1 ;

W1 = Wtemp1 ;

Wtemp2 = W2 - eta2 * Delta2 ;

W2 = Wtemp2 ;

end ;