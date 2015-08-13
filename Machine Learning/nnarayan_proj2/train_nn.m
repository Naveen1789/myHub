load('InputTrain.mat');
load('TargetTrain.mat');

% ######################################## Bias ########################################

% Bias : Add 1 to each row

biasInput = ones( size(InputTrain,1) , 1 );

InputTrain = [ biasInput InputTrain ];

eta = 0.1 ;

W1 = -1 + (2) * rand ( 20, size (InputTrain,2) );
DELTAA = zeros (20, size (InputTrain,2));

W2 = -1 + (2) * rand ( 10, 21 );
DELTAB = zeros (10,21) ;


deltah = zeros(21, 513);
deltao = zeros(10,21);

a2 = zeros(1,21);

y = zeros(1,10);

delta3 = zeros(1,10);

delta2 = zeros(1,21);

CrossEntropyErrorArr = zeros(1,1000);

for iter = 1 : 10,

	kk = randperm(size(InputTrain, 1));

	deltah = zeros(21, 513);
	deltao = zeros(10,21);

	for row = 1 : size (InputTrain,1),
	% for row = 1 : 1000,

	  CrossEntropyError = 0 ;
		k = kk(1,row) ;

		a2 = [ 1 sigmf( InputTrain(k,:) * W1' , [1 0]) ] ;

		y = sigmf( a2 * W2' , [1 0]) ;

		delta3 = y - TargetTrain(k,:);

		delta2 = ( delta3 * W2 ) .* ( a2 .* (1 - a2) );

		


		deltah = deltah + ( delta2' * InputTrain(k,:) ) ;


		deltao = deltao + ( delta3' * a2 ) ;		



		
		CrossEntropyError = CrossEntropyError + sum ( TargetTrain(k,:) .* log (y) );

	end ;

	CrossEntropyErrorArr(1,iter) = CrossEntropyError ;

	deltahTemp = deltah(2:21,:);
	DELTAA = ( deltahTemp ./ 19978 ) + eta * W1 ;

	DELTAB = ( deltao ./ 19978 ) + eta * W2 ;
	W1Temp = W1 - DELTAA;
	W1 = W1Temp ;

	W2Temp = W2 - DELTAB;
	W2 = W2Temp ;


	
end ;

%figure
%hold on
%title('number of iterations vs Cross entropy error')
%xlabel('number of iterations')
%ylabel('cross entropy error')

%plot(1:1000, CrossEntropyErrorArr, 'r');

%hold off


