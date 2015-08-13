function[W] = train_lr()

load('InputTrain.mat');
load('TargetTrain.mat');

% ######################################## Bias ########################################

% Bias : Add 1 to each row

biasInput = ones( size(InputTrain,1) , 1 );

InputTrain = [ biasInput InputTrain ];

size( InputTrain, 1)

% ################################ Logistic Regression #################################

CrsEntrErr = 0 ;

W = zeros ( 10, size (InputTrain,2) );

iter = 0;
eta = 0.0001 ;

while ( iter < 1000 ),
iter = iter + 1;
	y = sigmf( InputTrain * W' , [1 0]) ;

	Wnew = W - eta * (( y - TargetTrain )' * InputTrain ) ;

	W = Wnew ;

	CrsEntrErr = TargetTrain .* log(y) + ((1-TargetTrain).* log(1-y));

	CrsEntrErrArr(1,iter) = -sum(CrsEntrErr(:));
end;


figure
hold on
title('number of iterations vs Cross entropy error')
xlabel('number of iterations')
ylabel('cross entropy error')

plot(1:iter, CrsEntrErrArr, 'r');

hold off








