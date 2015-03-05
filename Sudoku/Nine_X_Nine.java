package sudoku;

import java.util.ArrayList;
import java.util.Scanner;

public class Nine_X_Nine {
	
	// Holds the Su-Do-Ku
	// Each element in the 2-d array is initialized to '0' ( ZERO )
	int[][] square = new int[10][10];
	
	ArrayList[][] elements = new ArrayList[10][10];
	
	// Su-Do-Ku complete ?
	boolean done = false ;
	
	boolean conti = false ;
	
	boolean dupFound = false ;
	
	public static int bkTrkCntr = 0 ;
	
//	public static int loopTrkCntr = 0 ;
	
	int iLeast = 0 ;
	
	int jLeast = 0 ;
	
	int leastSize = 9 ;
	
	public static void main ( String args[] ) {
		Nine_X_Nine ex = new Nine_X_Nine();
		ex.readElements();
		ex.displaySqr(ex.square);
		ex.setArrList();
		ex.displayArrList(ex.elements);
		
		ex.elements = ex.trim(ex.square , ex.elements );
		ex.square = ex.updateSqr( ex.square , ex.elements );
		
		ex.chkForCompletion(ex.square);
		
		while ( ( ! ex.done ) && ( ex.conti ) ) {
			
			ex.conti = false ;
			
			ex.elements = ex.trim(ex.square , ex.elements );
			ex.square = ex.updateSqr( ex.square , ex.elements );
			ex.chkForCompletion(ex.square);
			
		}
		
		if ( ex.chkForCompletion(ex.square) ) {
			if ( ex.chkIfCorrect( ex.square , ex.elements ) ) {
				System.out.println("This was Easy !!!");
				System.out.println("Recursive method was not called !!!");
				System.out.println("Yay !!!\nSudoku is correct and complete !!!");
				ex.displaySqr(ex.square);
			}
			
			// [Flow should never reach this block]
			else {
				System.out.println("oops !!!\nSudoku is Incorrect !!!");
				System.out.println("This should not have had happened !!!");
				ex.displaySqr(ex.square);
			}
			
		}
		else {
			
			System.out.println("This is tough !!!");
			ex.displaySqr(ex.square);
			ex.displayArrList(ex.elements);
			
			System.out.println("Calling the recursive method : ");
			
			ex.square = ex.backTracking( ex.square , ex.elements );
			
			if ( ex.square == null ) {
			System.out.println("This should not have had happened !!!");	
			}
			else {
				
				if ( ex.chkForCompletion(ex.square) ) {
					if ( ex.chkIfCorrect( ex.square , ex.elements) ) {
						System.out.println("Yay !!!\nSudoku is correct and complete !!!");
						ex.displaySqr(ex.square);
						ex.displayArrList(ex.elements);
					}
					else {
						
						if ( ex.chkIfCorrect(ex.square)) {
							
							System.out.println("Sudoku is correct and complete !!!");
							
							ex.displaySqr(ex.square);
							
						}
						else {
						ex.displayArrList(ex.elements);
						System.out.println("oops !!!\nSudoku is filled but is incorrect !!!");
						System.out.println("This should not have had happened !!!");
						ex.displaySqr(ex.square);
						}
					}
					
				}
				
				// [Flow should never reach this block]
				else {
					System.out.println("Status : Incomplete");
					ex.displaySqr(ex.square);
				}				
			}			
		}				
	}
	
	private void readElements() {
		
		int elem = 0 ;
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				boolean read = true ;
				
					do {
						// Reading the element for i th row and j th col
						System.out.println("Row : " + i + " , Col : " + j );
						System.out.println("Enter '0' if unknown.");
						Scanner in = new Scanner(System.in);
						read = in.hasNextInt() ;				
							
						if ( read ) {
						elem = in.nextInt();
						
						if ( elem < 0 || elem > 9 ) {
							elem = 10 ;
						}
											
						// To avoid entering an element already present in this Row / Collumn / Box.
					
						// Row
						if ( elem != 10 ) {
							for ( int k = 1 ; k < j ; k++ ) {
								if ( square[i][k] == elem && elem != 0 ) {
									System.out.println(elem + " already exists in this row.");
									elem = 10 ;
								}
							}
						}

						// Collumn						
						// if elem = 10 , duplicate already found
						if ( elem != 10 ) {
							for ( int l = 1 ; l < i ; l++ ) {
								if ( square[l][j] == elem && elem != 0 ) {
									System.out.println(elem + " already exists in this collumn.");
									elem = 10 ;
								}
							}
						}
					
						// block of code to remove duplicate elements from the box
						// if elem = 10 , duplicate already found
						if ( elem != 10 ) {
							removeDuplicateElementsInTheBox : {
						
							int xMin = 0 , xMax = 0 ;
							int yMin = 0 , yMax = 0 ;
							
							if ( j % 3 == 0 ) {

								xMin = j - 2 ;
								xMax = j ;
							}
					
							if ( j % 3 == 1 ) {

								xMin = j ;
								xMax = j + 2 ;
					
							}
							
							if ( j % 3 == 2 ) {

								xMin = j - 1 ;
								xMax = j + 1 ;
					
							}
							
							if ( i % 3 == 0 ) {

								yMin = i - 2 ;
								yMax = i ;
							}
									
							if ( i % 3 == 1 ) {

								yMin = i ;
								yMax = i + 2 ;
					
							}
							
							if ( i % 3 == 2 ) {

								yMin = i - 1 ;
								yMax = i + 1 ;
					
							}
					
								for ( int tempY = yMin ; tempY <= yMax ; tempY++ ) {
									for ( int tempX = xMin ; tempX <= xMax ; tempX++ ) {
							
										if ( square[tempY][tempX] == elem && elem != 0 ) {								
											System.out.println("i : " + tempY + " , j : " + tempX );											
											System.out.println(elem + " already exists in this box.");
											elem = 5 ;
										}
										if ( tempX >= j ) {
											break ;
										}
									}
									if ( tempY >= i ) {
										break ;
									}
								}
							}
						}
							}
						else {
							System.out.println("Invalid input.\nOnly enter digits netween 1 & 9.");
							elem = 10 ;
						}
									
					} while( elem < 0 || elem > 9 );
				
					square[i][j] = elem ;
				
			}
		}			
	}
	
	private void displaySqr( int[][] sqr ) {
		
		for ( int i = 1 ; i < sqr.length ; i++ ) {
			
			for ( int j = 1 ; j < sqr[i].length ; j++ ) {
				System.out.print(sqr[i][j]);
				if( j % 3 == 0 ) {
					System.out.print(" ");
				}
			}
			System.out.println("");
			if ( i % 3 == 0 ) {
				System.out.println("");
			}
		}
	}
	
	private void setArrList() {
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				elements[i][j] = new ArrayList();
				
				if ( square[i][j] != 0 ) {				
					elements[i][j].add(square[i][j]);
				}
				else {	
					for ( int addThis = 1 ; addThis <= 9 ; addThis++ ) {
						elements[i][j].add(addThis);
					}
				}
			}
		}
	
	}
	
	private void displayArrList( ArrayList[][] elem ) {
		
		for ( int i = 1 ; i < elem.length ; i++ ) {
			for ( int j = 1 ; j < elem[i].length ; j++ ) {
				System.out.println("Arr List at [" + i + "][" + j + "] -> " + elem[i][j]);
			}
		}
	}
	
	private ArrayList[][] trim( int[][] sqr , ArrayList[][] elementsTemp ) {
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				
				int elem = sqr[i][j] ;
												
				if ( elem != 0 ) {
					
					Integer i1 = new Integer(elem);		
					
					// Remove all elements except the given element from this arrayList
					
					int iLocal = 0 ;
					int thisSize = elements[i][j].size() ;
					for ( int temp = 0 ; temp < thisSize ; temp++ ) {
						if ( (int)elements[i][j].get(iLocal) == i1 ) {
							iLocal++ ;							
						}
						else {
							elements[i][j].remove(iLocal);
						}
					}
					
					// Remove the 'elem' from this collumn
					for ( int l = 1 ; l <= 9 ; l++ ) {
						if ( l != i ) {
							if ( elementsTemp[l][j].contains(i1) ) {
								
								conti = true ;
								elementsTemp[l][j].remove(i1);

							}

						}
					}
					// Remove the 'elem' from this row					
					for ( int k = 1 ; k <= 9 ; k++ ) {
						if ( k != j ) {
							if ( elementsTemp[i][k].contains(i1) ) {
								
								conti = true ;
								elementsTemp[i][k].remove(i1);
							}
						}
					}
					
					
					removeElemFromThisBox : {
						
						int xMin = 0 , xMax = 0 ;
						int yMin = 0 , yMax = 0 ;
						
						if ( j % 3 == 0 ) {

							xMin = j - 2 ;
							xMax = j ;
						}
				
						if ( j % 3 == 1 ) {

							xMin = j ;
							xMax = j + 2 ;
				
						}
						
						if ( j % 3 == 2 ) {

							xMin = j - 1 ;
							xMax = j + 1 ;
				
						}
						
						if ( i % 3 == 0 ) {

							yMin = i - 2 ;
							yMax = i ;
						}
								
						if ( i % 3 == 1 ) {

							yMin = i ;
							yMax = i + 2 ;
				
						}
						
						if ( i % 3 == 2 ) {

							yMin = i - 1 ;
							yMax = i + 1 ;
				
						}
					
					for ( int tempY = yMin ; tempY <= yMax ; tempY++ ) {
						for ( int tempX = xMin ; tempX <= xMax ; tempX++ ) {

							if ( ! ( tempY == i && tempX == j ) ) {
								if (elementsTemp[tempY][tempX].contains(i1) ) {
								conti = true ;
								elementsTemp[tempY][tempX].remove(i1);
								}
							}
						}

					}
					}
					
					
					
					
					
				}
			}	
		}
		

		
		return elementsTemp ;
	}
	
	private int[][] updateSqr( int[][] squareTemp , ArrayList[][] elementsTemp ) {
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				
				if ( ( elementsTemp[i][j].size() == 1 && squareTemp[i][j] == 0 )) {
					
					squareTemp[i][j] = (int)elementsTemp[i][j].get(0);
				}
			}
		}
		
		return squareTemp ;
		
	}
	
	private boolean chkForCompletion( int[][] sqr ) {
		
		done = true ;
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				if ( sqr[i][j] == 0 ) {
					done = false ;
					break ;
				}
			}
			if ( !done ) {
				break ;
				
			}
			
		}
		System.out.println("Su-Do-Ku complete ? " + done );
		return done ;
		
	}
	
	private boolean chkIfCorrect( int[][] sqr , ArrayList[][] elems ) {
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
		for ( int j = 1 ; j < 9 ; j++ ) {
			for ( int k = ( j + 1 ) ; k <= 9 ; k++ ) {
				dupFound = false ;
				if ( ( sqr[i][j] != 0 ) && ( sqr[i][j] == sqr[i][k] ) ) {
					System.out.println("Duplicate found in the row : " + i );
					dupFound = true ;
					break ;
				}
			}
			if ( dupFound ) {
				break ;
			}
		}
		if ( dupFound ) {
			break ;
		}
		}
		
		if ( dupFound ) {
			return ( false )  ;
		}
		
		for ( int a = 1 ; a <= 9 ; a++ ) {
			for ( int b = 1 ; b < 9 ; b++ ) {
				for ( int c = ( b + 1 ) ; c <= 9 ; c++ ) {
					if ( ( sqr[b][a] != 0 ) && ( sqr[b][a] == sqr[c][a] ) ) {
						System.out.println("Duplicate found in the col : " + a );
						dupFound = true ;
						break ;
					}
				}
				if ( dupFound ) {
					break ;
				}
			}
			if ( dupFound ) {
				break ;
			}
	
		}
		
		if ( dupFound ) {
			return ( false )  ;
		}
		
		checkForDupElementsInTheBox : {
			

		
		for ( int i =1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				
				int xMin = 0 , xMax = 0 ;
				int yMin = 0 , yMax = 0 ;
				
				if ( j % 3 == 0 ) {

					xMin = j - 2 ;
					xMax = j ;
				}
		
				if ( j % 3 == 1 ) {

					xMin = j ;
					xMax = j + 2 ;
		
				}
				
				if ( j % 3 == 2 ) {

					xMin = j - 1 ;
					xMax = j + 1 ;
		
				}
				
				if ( i % 3 == 0 ) {

					yMin = i - 2 ;
					yMax = i ;
				}
						
				if ( i % 3 == 1 ) {

					yMin = i ;
					yMax = i + 2 ;
		
				}
				
				if ( i % 3 == 2 ) {

					yMin = i - 1 ;
					yMax = i + 1 ;
		
				}
				
				for ( int tempY = yMin ; tempY <= yMax ; tempY++ ) {
					for ( int tempX = xMin ; tempX <= xMax ; tempX++ ) {

						if ( ! ( tempY == i && tempX == j ) ) {
						if ( ( sqr[i][j] != 0 ) && ( sqr[i][j] == sqr[tempY][tempX] ) ) {
							System.out.println("Duplicate found." );
							dupFound = true ;
							return ( false )  ;
						}
						}
					}
				}
				
			}
		}

		}
		
		for ( int a1 = 1 ; a1 <= 9 ; a1++ ) {
			for ( int b1 = 1 ; b1 <= 9 ; b1++ ) {
				if ( elems[a1][b1].size() == 0 ) {
					return false ;
				}
			}
		}
		
		return ( ! dupFound ) ;
		
	}
	
	private boolean chkIfCorrect( int[][] sqr ) {
		
		for ( int i = 1 ; i <= 9 ; i++ ) {
		for ( int j = 1 ; j < 9 ; j++ ) {
			for ( int k = ( j + 1 ) ; k <= 9 ; k++ ) {
				dupFound = false ;
				if ( ( sqr[i][j] != 0 ) && ( sqr[i][j] == sqr[i][k] ) ) {
					System.out.println("Duplicate found in the row : " + i );
					dupFound = true ;
					break ;
				}
			}
			if ( dupFound ) {
				break ;
			}
		}
		if ( dupFound ) {
			break ;
		}
		}
		
		if ( dupFound ) {
			return ( false )  ;
		}
		
		for ( int a = 1 ; a <= 9 ; a++ ) {
			for ( int b = 1 ; b < 9 ; b++ ) {
				for ( int c = ( b + 1 ) ; c <= 9 ; c++ ) {
					if ( ( sqr[b][a] != 0 ) && ( sqr[b][a] == sqr[c][a] ) ) {
						System.out.println("Duplicate found in the col : " + a );
						dupFound = true ;
						break ;
					}
				}
				if ( dupFound ) {
					break ;
				}
			}
			if ( dupFound ) {
				break ;
			}
	
		}
		
		if ( dupFound ) {
			return ( false )  ;
		}
		
		checkForDupElementsInTheBox : {
			

		
		for ( int i =1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				
				int xMin = 0 , xMax = 0 ;
				int yMin = 0 , yMax = 0 ;
				
				if ( j % 3 == 0 ) {

					xMin = j - 2 ;
					xMax = j ;
				}
		
				if ( j % 3 == 1 ) {

					xMin = j ;
					xMax = j + 2 ;
		
				}
				
				if ( j % 3 == 2 ) {

					xMin = j - 1 ;
					xMax = j + 1 ;
		
				}
				
				if ( i % 3 == 0 ) {

					yMin = i - 2 ;
					yMax = i ;
				}
						
				if ( i % 3 == 1 ) {

					yMin = i ;
					yMax = i + 2 ;
		
				}
				
				if ( i % 3 == 2 ) {

					yMin = i - 1 ;
					yMax = i + 1 ;
		
				}
				
				for ( int tempY = yMin ; tempY <= yMax ; tempY++ ) {
					for ( int tempX = xMin ; tempX <= xMax ; tempX++ ) {

						if ( ! ( tempY == i && tempX == j ) ) {
						if ( ( sqr[i][j] != 0 ) && ( sqr[i][j] == sqr[tempY][tempX] ) ) {
							System.out.println("Duplicate found." );
							dupFound = true ;
							return ( false )  ;
						}
						}
					}
				}
				
			}
		}

		}
				
		return ( ! dupFound ) ;
		
	}
	

	
	private int[][] backTracking ( int[][] squareTemp , ArrayList[][] elementsTemp ) {
		
		int[][] sqr = new int[10][10];
		
		int loopTrkCntr = 0 ;
				
		bkTrkCntr++ ;
		System.out.println("Recursive Call number : " + bkTrkCntr );
		
		int thisRecCall = bkTrkCntr ;
		
//		displaySqr(squareTemp);
//		displayArrList(elementsTemp);
		
		int iTemp = 1 ;
		int jTemp = 1 ;
		int size = 0 ;
		int leastSize = 9 ;
		
		// Find the smallest Array List
		for ( int itr1 = 1 ; itr1 <= 9 ; itr1++ ) {
			for ( int itr2 = 1 ; itr2 <= 9 ; itr2++ ) {
				size = elementsTemp[itr1][itr2].size();
				if ( ( squareTemp[itr1][itr2] == 0 ) && ( size > 1 && size <= leastSize ) ) {
					iTemp = itr1 ;
					jTemp = itr2 ;
					leastSize = size ;
				}
			}
		}
		
		System.out.println("Smallest ArrayList present at : " + iTemp + " , " + jTemp );
						
		System.out.println("ArrayList at : [" + iTemp + "][" + jTemp + "] : " + elementsTemp[iTemp][jTemp] );
		
		loopTrkCntr = 0 ;
		
		for ( int i = 0 ; i < leastSize ; i++ ) {			
			
			loopTrkCntr++ ;
			System.out.print("Rec number : " + thisRecCall );
			System.out.println("\tLoop counter : " + loopTrkCntr );
			
			System.out.println("ArrayList at : [" + iTemp + "][" + jTemp + "] : " + elementsTemp[iTemp][jTemp] );
			
			Integer iTemp1 = (int) elementsTemp[iTemp][jTemp].get(i);
			
			// Duplicates , local to this method
			
			// Duplicate the sqr
			int[][] newSquareTemp = new int[10][10] ;
			for ( int i1 = 1 ; i1 <= 9 ; i1++ ) {
				for ( int j1 = 1 ; j1 <= 9 ; j1++ ) {
					newSquareTemp[i1][j1] = squareTemp[i1][j1] ;
				}
			}
			
			// Duplicate the ArrayLists
			ArrayList[][] newElementsTemp = new ArrayList[10][10];
			for ( int i2 = 1 ; i2 <= 9 ; i2++ ) {
				for ( int j2 = 1 ; j2 <= 9 ; j2++ ) {
						newElementsTemp[i2][j2] = new ArrayList();
						newElementsTemp[i2][j2].addAll(elementsTemp[i2][j2]);
				}
			}
			
			newSquareTemp[iTemp][jTemp] = iTemp1 ;
			
			if ( chkIfCorrect( newSquareTemp , newElementsTemp ) ) {
				
				do {
					conti = false ;
					
					newElementsTemp = trim( newSquareTemp , newElementsTemp );
					newSquareTemp = updateSqr( newSquareTemp , newElementsTemp );
					
				} while ( ( conti ) && ( chkIfCorrect( newSquareTemp , newElementsTemp ) ) );
				
				chkForCompletion(newSquareTemp);
				
				if ( !( chkIfCorrect( newSquareTemp , newElementsTemp ) ) ) {					
					System.out.println("Not correct");										
				}
				else if( ( chkIfCorrect( newSquareTemp , newElementsTemp ) ) && ( done ) ){
					return newSquareTemp ;
				}
				else if ( ( chkIfCorrect( newSquareTemp , newElementsTemp ) ) && ( !conti ) && ( !done )) {
					System.out.println("Correct , but incomplete...");
					System.out.println("Calling recursion again");
					newSquareTemp = backTracking( newSquareTemp , newElementsTemp );
					if ( chkForCompletion(newSquareTemp) &&  chkIfCorrect( newSquareTemp , newElementsTemp ) ) {
						return newSquareTemp ;
					}
//					sqr = newSquareTemp ;
//					return newSquareTemp ;
			}
			}
			
			
		}
		
		bkTrkCntr-- ;
		return sqr ;

	}
}



/*
Rough Space :

trim() -> Remove elements based on a Number's limited positions.
		{
			for ( int num = 1 ; num <= 9 ; num++ ) {
				int reps = 0 ;
				
				for ( int i = 1 ; i <= 9 ; i++ ) {
					for ( int j = 1 ; j <= 9 ; j++ ) {
						if ( elementsTemp[i][j].contains(num)) {
							
							reps++ ;
						}
						
					}
					if ( reps == 1 ) {
						
					}
				}
			}
		}
		
		===================================
		
			private int[][] newBackTracking ( int[][] squareTemp , ArrayList[][] elementsTemp ) {
		
		// Duplicate the sqr
		int[][] newSquareTemp = new int[10][10] ;
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				newSquareTemp[i][j] = squareTemp[i][j] ;
			}
		}
		
		// Duplicate the ArrayLists
		ArrayList[][] newElementsTemp = new ArrayList[10][10];
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
					newElementsTemp[i][j] = new ArrayList();
					newElementsTemp[i][j].addAll(elementsTemp[i][j]);
			}
		}
		
		int iTemp = 1 ;
		int jTemp = 1 ;
		int size = 0 ;
		int leastSize = 9 ;
		
		// Find the smallest Array List
		for ( int itr1 = 1 ; itr1 <= 9 ; itr1++ ) {
			for ( int itr2 = 1 ; itr2 <= 9 ; itr2++ ) {
				size = newElementsTemp[itr1][itr2].size();
				if ( size > 1 && size <= leastSize ) {
					iTemp = itr1 ;
					jTemp = itr2 ;
					leastSize = size ;
				}
			}
		}
		

		System.out.println("Smallest ArrayList present at : " + iTemp + " , " + jTemp );
						
		System.out.println("ArrayList at : [" + iTemp + "][" + jTemp + "] : " + newElementsTemp[iTemp][jTemp] );
		
		// Get the first element from the smallest Array List
		
		Integer iTemp1 = (int) newElementsTemp[iTemp][jTemp].get(0);
		
		// Substitute this element at the right pos in the sqr
		newSquareTemp[iTemp][jTemp] = iTemp1 ;
		
		if ( chkIfCorrect(newSquareTemp) ) {
			
		}
		
		else {
			newElementsTemp[iTemp][jTemp].remove(0);
			
			if ( newElementsTemp[iTemp][jTemp].size() == 1 ) {
				newSquareTemp[iTemp][jTemp] = ( int ) newElementsTemp[iTemp][jTemp].get(0);
				
				if ( chkIfCorrect(newSquareTemp) ) {
					
				}
				else {
					return null ;
				}
			}
			else {
				
			}
		}
		return null ;
		
	}
*/


/*
Back Up : 

	private int[][] backTracking ( int[][] squareTemp , ArrayList[][] elementsTemp ) {
		
		// Duplicates , local to this method
		
		// Duplicate the sqr
		int[][] newSquareTemp = new int[10][10] ;
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
				newSquareTemp[i][j] = squareTemp[i][j] ;
			}
		}
		
		// Duplicate the ArrayLists
		ArrayList[][] newElementsTemp = new ArrayList[10][10];
		for ( int i = 1 ; i <= 9 ; i++ ) {
			for ( int j = 1 ; j <= 9 ; j++ ) {
					newElementsTemp[i][j] = new ArrayList();
					newElementsTemp[i][j].addAll(elementsTemp[i][j]);
			}
		}

		int iTemp = 1 ;
		int jTemp = 1 ;
		int size = 0 ;
		int leastSize = 9 ;
		
		// Find the smallest Array List
		for ( int itr1 = 1 ; itr1 <= 9 ; itr1++ ) {
			for ( int itr2 = 1 ; itr2 <= 9 ; itr2++ ) {
				size = newElementsTemp[itr1][itr2].size();
				if ( size > 1 && size <= leastSize ) {
					iTemp = itr1 ;
					jTemp = itr2 ;
					leastSize = size ;
				}
			}
		}
		
		displaySqr(newSquareTemp);
		
		System.out.println("Smallest ArrayList present at : " + iTemp + " , " + jTemp );
						
		System.out.println("ArrayList at : [" + iTemp + "][" + jTemp + "] : " + newElementsTemp[iTemp][jTemp] );
		
		// Get the first element from the smallest Array List
		
		Integer iTemp1 = (int) newElementsTemp[iTemp][jTemp].get(0);
		
		// Substitute this element at the right pos in the sqr
		newSquareTemp[iTemp][jTemp] = iTemp1 ;
		
		if ( chkIfCorrect(newSquareTemp) ) {
		
		System.out.println("Placing " + iTemp1 + " at " + iTemp + " , " + jTemp );
		
		displaySqr(newSquareTemp);
		
		// Update the Array List
		
		int limit = newElementsTemp[iTemp][jTemp].size();
		int i = 0 ;
		for ( int temp = 0 ; temp < limit ; temp++ ) {
			Integer intTemp2 = (int)newElementsTemp[iTemp][jTemp].get(i);
			if ( intTemp2 == iTemp1 ) {
				i++ ;
			}
			else {
//				System.out.println("Removing " + intTemp2 + " from the Array List");
				newElementsTemp[iTemp][jTemp].remove(intTemp2);				
			}
		}
		
		System.out.println("Updated ArrayList : " + newElementsTemp[iTemp][jTemp]);

		conti = false ;
		
		newElementsTemp = trim( newSquareTemp , newElementsTemp );
		newSquareTemp = updateSqr( newSquareTemp , newElementsTemp );
		
		conti = chkIfCorrect(newSquareTemp);
		
		if ( ! chkIfCorrect(newSquareTemp) ) {
			return null ;
		}
		
		while ( conti ) {
			conti = false ;
			
			newElementsTemp = trim( newSquareTemp , newElementsTemp );
			newSquareTemp = updateSqr( newSquareTemp , newElementsTemp );

		}
		
		if ( chkForCompletion(newSquareTemp) ) {
//			boolean correct = chkIfCorrect(newSquareTemp);
//			
//			if ( correct ) {
//				System.out.println("Sudoku is Correct");
//				return newSquareTemp ;
//			}
//			
//			else {
//				
//				System.out.println("Sudoku is Incorrect");
//				
//
////				// Remove iTemp1
////				elementsTemp[iTemp][jTemp].remove(iTemp1);
////				newSquareTemp = backTracking( squareTemp , elementsTemp );
//				
//			}
				
			return newSquareTemp ;
		}
		else {
			
			displaySqr(newSquareTemp);
			newSquareTemp = backTracking( newSquareTemp , newElementsTemp );
			
		}
		}
		
		else {
			// Back Trace
			if ( elementsTemp[iTemp][jTemp].size() == 1 ) {
//				return backTracking();
				
				
				
			}
			
			elementsTemp[iTemp][jTemp].remove(0);
			

			
			conti = true ;
			
			while ( conti ) {
				
				conti = false ;
				
				elementsTemp = trim( squareTemp , elementsTemp );
				squareTemp = updateSqr( squareTemp , elementsTemp );				
			}
			
			newSquareTemp = backTracking( squareTemp , elementsTemp );
			
		}
		
		return newSquareTemp ;	
	}
	
	
	================================
	
	
			// Get the first element from the smallest Array List
		
		Integer iTemp1 = (int) newElementsTemp[iTemp][jTemp].get(0);
		
		// Substitute this element at the right pos in the sqr
		newSquareTemp[iTemp][jTemp] = iTemp1 ;
		
		if ( chkIfCorrect(newSquareTemp) ) {
			
		}
		
		else {
			
			newSquareTemp[iTemp][jTemp] = 0 ; // Reverting back to '0'
			
			newElementsTemp[iTemp][jTemp].remove(0); // remove the selected element
			
			if ( newElementsTemp[iTemp][jTemp].size() == 0 ) {
				return null ;
			}
			else {
				
				squareTemp = backTracking( newSquareTemp , newElementsTemp );
				
			}
			
		}


=============================================

	private int[][] backTracking ( int[][] squareTemp , ArrayList[][] elementsTemp ) {
		
		int[][] sqr = new int[5][5];
		
		int loopTrkCntr = 0 ;
				
		bkTrkCntr++ ;
		System.out.println("Recursive Call number : " + bkTrkCntr );
		
		int thisRecCall = bkTrkCntr ;
		
//		displaySqr(squareTemp);
//		displayArrList(elementsTemp);
		
		int iTemp = 1 ;
		int jTemp = 1 ;
		int size = 0 ;
		int leastSize = 9 ;
		
		// Find the smallest Array List
		for ( int itr1 = 1 ; itr1 <= 9 ; itr1++ ) {
			for ( int itr2 = 1 ; itr2 <= 9 ; itr2++ ) {
				size = elementsTemp[itr1][itr2].size();
				if ( ( squareTemp[itr1][itr2] == 0 ) && ( size > 1 && size <= leastSize ) ) {
					iTemp = itr1 ;
					jTemp = itr2 ;
					leastSize = size ;
				}
			}
		}
		
		System.out.println("Smallest ArrayList present at : " + iTemp + " , " + jTemp );
						
		System.out.println("ArrayList at : [" + iTemp + "][" + jTemp + "] : " + elementsTemp[iTemp][jTemp] );
		
		loopTrkCntr = 0 ;
		
		for ( int i = 0 ; i < leastSize ; i++ ) {			
			
			loopTrkCntr++ ;
			System.out.print("Rec number : " + thisRecCall );
			System.out.println("\tLoop counter : " + loopTrkCntr );
			
			System.out.println("ArrayList at : [" + iTemp + "][" + jTemp + "] : " + elementsTemp[iTemp][jTemp] );
			
			Integer iTemp1 = (int) elementsTemp[iTemp][jTemp].get(i);
			
			squareTemp[iTemp][jTemp] = iTemp1 ;
			
			if ( chkIfCorrect( squareTemp , elementsTemp ) ) {
				
				squareTemp[iTemp][jTemp] = 0 ; // Reverting back to '0'
				
				// Duplicates , local to this method
				
				// Duplicate the sqr
				int[][] newSquareTemp = new int[10][10] ;
				for ( int i1 = 1 ; i1 <= 9 ; i1++ ) {
					for ( int j1 = 1 ; j1 <= 9 ; j1++ ) {
						newSquareTemp[i1][j1] = squareTemp[i1][j1] ;
					}
				}
				
				// Duplicate the ArrayLists
				ArrayList[][] newElementsTemp = new ArrayList[10][10];
				for ( int i2 = 1 ; i2 <= 9 ; i2++ ) {
					for ( int j2 = 1 ; j2 <= 9 ; j2++ ) {
							newElementsTemp[i2][j2] = new ArrayList();
							newElementsTemp[i2][j2].addAll(elementsTemp[i2][j2]);
					}
				}
				
				newSquareTemp[iTemp][jTemp] = iTemp1 ;
				
				do {
					conti = false ;
					
					newElementsTemp = trim( newSquareTemp , newElementsTemp );
					newSquareTemp = updateSqr( newSquareTemp , newElementsTemp );
					
				} while ( ( conti ) && ( chkIfCorrect( newSquareTemp , newElementsTemp ) ) );
				
				chkForCompletion(newSquareTemp);
				
				if ( !( chkIfCorrect( newSquareTemp , newElementsTemp ) ) ) {
					squareTemp[iTemp][jTemp] = 0 ;
					
					System.out.println("Not correct");
					continue ;
				}
				
				else if ( ( chkIfCorrect( newSquareTemp , newElementsTemp ) ) && ( done ) ) {
					
					sqr = newSquareTemp ;
					System.out.println("Complete");
					return sqr ;
				}
				
				else if ( ( chkIfCorrect( newSquareTemp , newElementsTemp ) ) && ( !conti ) && ( !done )) {
					System.out.println("Correct , but incomplete...");
					System.out.println("Calling recursion again");
					newSquareTemp = backTracking( newSquareTemp , newElementsTemp );
				}
				
			}
			else {
				squareTemp[iTemp][jTemp] = 0 ;
			}
		}
		
		return squareTemp ;
	}
	
	
	
====================================================
*/
