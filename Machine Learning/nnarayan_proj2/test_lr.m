function [ errRate, reciprocalRank, y ] = test_lr()

load('W_LR.mat')
load('InputTest.mat');
load('TargetTest.mat');

% ######################################## Bias ########################################

% Bias : Add 1 to each row

biasInput = ones( size(InputTest,1) , 1 );

InputTest = [ biasInput InputTest ];

size( InputTest, 1)

% ######################################## Test ########################################

yTemp = sigmf( InputTest * W' , [1 0]) ;

% ###################################### Error Rate ####################################

% ################################### Assign to class ##################################

y = zeros(size(yTemp,1),size(yTemp,2));

for i = 1  : size(y,1),

	[classVal class] = max(yTemp(i,:));

	y(i,class) = 1 ;
end;

% ################################# Misclassifications #################################


misClassifications = 0 ;

reciprocalRank = 0 ;
reciprocalRank1 = 0 ;
reciprocalRank2 = 0 ;

for i = 1  : size(y,1),
	if ( y(i,:) == TargetTest(i,:) )

		reciprocalRank1 = reciprocalRank1 + 1 ;

	else
		misClassifications = misClassifications + 1 ;

% #################################### Reciprocal Rank #################################

		% Find the right class
		rankedOrder = zeros(1,size (yTemp,2));

		[ActualclassVal Actualclass] = max(TargetTest(i,:));

		for j = 1 : size (yTemp,2),
			rank = 1 ;
			for k = 1 : size (yTemp,2),
				if (yTemp(i,j) < yTemp(i,k)) ,
					rank = rank + 1 ;
				end ;
		
				rankedOrder(1,j) = rank ;

			end;
		end;
		
		yTemp(i,:)
		y(i,:)

		TargetTest(i,:)

		rankedOrder
		rankedOrder(1,Actualclass)

		reciprocalRank2 = reciprocalRank2 + ( 1 / (rankedOrder(1,Actualclass)) ) ;

% #################################### Reciprocal Rank #################################

	end;
end;

errRate = misClassifications / 15 ;
reciprocalRank = ( reciprocalRank1 + reciprocalRank2 ) / 15 ;

% ################################# Misclassifications #################################

