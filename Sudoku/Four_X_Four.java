package sudoku;

import java.util.ArrayList;
import java.util.Scanner;

public class Four_X_Four {
	
	// Holds the Su-Do-Ku
	// Each element in the 2-d array is initialized to '0' ( ZERO )
	int[][] square = new int[5][5];
	
	ArrayList[][] elements = new ArrayList[5][5];
	
	// Su-Do-Ku complete ?
	boolean done = false ;
	
	boolean conti = false ;
	
	boolean dupFound = false ;
	
	int iLeast = 0 ;
	
	int jLeast = 0 ;
	
	int leastSize = 4 ;
	
	public static void main ( String args[] ) {
		
		Four_X_Four ex = new Four_X_Four();
		
//		ex.displaySqr();
		ex.readElements();
		ex.displaySqr();
		ex.setArrList();
		ex.displayArrList();
		ex.trim();
		ex.updateSqr();
		System.out.println("After Initial trim() & updateSqr()");
		
		ex.displaySqr();
		ex.displayArrList();
		
		ex.chkForCompletion();
		

		
		while ( ( ! ex.done ) && ( ex.conti ) ) {
			
			ex.conti = false ;
			
			ex.trim();
			ex.updateSqr();
			ex.displaySqr();
			ex.chkForCompletion();
		}
		if ( ex.done ) {
		System.out.println("Status : Complete !!!");
		}
		else {
		System.out.println("Status : Incomplete !!!");
		ex.displaySqr();
		System.out.println("Calling the recursive method : ");
		ex.square = ex.backTracking( ex.square , ex.elements );
		
		ex.displaySqr();
		
		ex.chkForCompletion();
		System.out.println("After Calling the recursive method : ");
		if ( ex.done ) {
		System.out.println("Complete !!!");
		}
		else {
			System.out.println("Still Incomplete !!!");	
		}
		
		}
		ex.displaySqr();
		
		

		
	}
	
	private int[][] backTracking ( int[][] squareTemp , ArrayList[][] elementsTemp ) {
		
		// Duplicates , local to this method
		
		// Duplicate the sqr
		int[][] newSquareTemp = new int[5][5] ;
		for ( int i = 0 ; i < 5 ; i++ ) {
			for ( int j = 0 ; j < 5 ; j++ ) {
				newSquareTemp[i][j] = squareTemp[i][j] ;
			}
		}
//		newSquareTemp = squareTemp ;
		
		// Duplicate the ArrayLists
		ArrayList[][] newElementsTemp = new ArrayList[5][5];
		for ( int i = 1 ; i < 5 ; i++ ) {
			for ( int j = 1 ; j < 5 ; j++ ) {
//				System.out.println("i : " + i + " , j : " + j );
//				System.out.println("elementsTemp[" + i + "][" + j + "] : " + elementsTemp[i][j] );
					newElementsTemp[i][j] = new ArrayList();
					newElementsTemp[i][j].addAll(elementsTemp[i][j]);
			}
		}
		
//		newElementsTemp = elementsTemp ;
			
		int iTemp = 1 ;
		int jTemp = 1 ;
		int size = 1 ;
		int leastSize = 4 ;
		
		// Pick an element from the smallest Array List
		for ( int itr1 = 1 ; itr1 <= 4 ; itr1++ ) {
			for ( int itr2 = 1 ; itr2 <=4 ; itr2++ ) {
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
		
		System.out.println("Placing " + iTemp1 + " at " + iTemp + " , " + jTemp );
		
		// Update the Array List
		
		int limit = newElementsTemp[iTemp][jTemp].size();
		int i = 0 ;
		for ( int temp = 0 ; temp < limit ; temp++ ) {
			Integer intTemp2 = (int)newElementsTemp[iTemp][jTemp].get(i);
			if ( intTemp2 == iTemp1 ) {
				i++ ;
			}
			else {
				System.out.println("Removing " + intTemp2 + " from the Array List");
				newElementsTemp[iTemp][jTemp].remove(intTemp2);
				
			}
		}
		
		System.out.println("Updated ArrayList : " + newElementsTemp[iTemp][jTemp]);

		conti = false ;
		
		newElementsTemp = trim( newSquareTemp , newElementsTemp );
		newSquareTemp = updateSqr( newSquareTemp , newElementsTemp );
		
		while ( conti ) {
			conti = false ;
			
			newElementsTemp = trim( newSquareTemp , newElementsTemp );
			newSquareTemp = updateSqr( newSquareTemp , newElementsTemp );

		}
		
		if ( chkForCompletion(newSquareTemp) ) {
			boolean correct = chkIfCorrect(newSquareTemp);
			
			if ( correct ) {
				System.out.println("Sudoku is Correct");
				return newSquareTemp ;
			}
			
			else {
				
				System.out.println("Sudoku is Incorrect");
				

//				// Remove iTemp1
//				elementsTemp[iTemp][jTemp].remove(iTemp1);
//				newSquareTemp = backTracking( squareTemp , elementsTemp );
				
			}
						
		}
		else {
			displaySqr(newSquareTemp);
			newSquareTemp = backTracking( newSquareTemp , newElementsTemp );
			
		}
		
		return newSquareTemp ;	
	}
	
	private void readElements() {
		
		int elem = 0 ;
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				boolean read = true ;
				
					do {
						// Reading the element for i th row and j th col
						System.out.println("Row : " + i + " , Col : " + j );
						System.out.println("Enter '0' if unknown.");
						Scanner in = new Scanner(System.in);
						read = in.hasNextInt() ;				
							
						if ( read ) {
						elem = in.nextInt();
						
						if ( elem < 0 || elem > 4 ) {
							elem = 5 ;
						}
						// To avoid entering an element already present in this Row / Collumn / Box.
					
						// Row
						if ( elem != 5 ) {
							for ( int k = 1 ; k < j ; k++ ) {
								if ( square[i][k] == elem && elem != 0 ) {
									System.out.println(elem + " already exists in this row.");
									elem = 5 ;
								}
							}
						}

						// Collumn						
						// if elem = 5 , duplicate already found
						if ( elem != 5 ) {
							for ( int l = 1 ; l < i ; l++ ) {
								if ( square[l][j] == elem && elem != 0 ) {
									System.out.println(elem + " already exists in this collumn.");
									elem = 5 ;
								}
							}
						}
					
						// block of code to remove duplicate elements from the box
						// if elem = 5 , duplicate already found
						if ( elem != 5 ) {
							removeDuplicateElementsInTheBox : {
						
								int xMin = 0 , xMax = 0 ;
								int yMin = 0 , yMax = 0 ;
								
								if ( j % 2 == 0 ) {

									xMin = j - 1 ;
									xMax = j ;
								}
						
								if ( j % 2 == 1 ) {

									xMin = j ;
									xMax = j + 1 ;
						
								}
								
								if ( i % 2 == 0 ) {

									yMin = i - 1 ;
									yMax = i ;
								}
										
								if ( i % 2 == 1 ) {

									yMin = i ;
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
							System.out.println("Invalid input.\nOnly enter digits netween 1 & 4.");
							elem = 5 ;
						}
									
					} while( elem < 0 || elem > 4 );
				
					square[i][j] = elem ;
				
			}
		}			
	}
	
	private void displaySqr() {
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				System.out.print(square[i][j]);
				if( j % 2 == 0 ) {
					System.out.print(" ");
				}
			}
			System.out.println("");
			if ( i % 2 == 0 ) {
				System.out.println("");
			}
		}
	}
	
	private void displaySqr( int[][] sqr ) {
		
		for ( int i = 1 ; i < sqr.length ; i++ ) {
			
			for ( int j = 1 ; j < sqr[i].length ; j++ ) {
				System.out.print(sqr[i][j]);
				if( j % 2 == 0 ) {
					System.out.print(" ");
				}
			}
			System.out.println("");
			if ( i % 2 == 0 ) {
				System.out.println("");
			}
		}
	}
	
	private void setArrList() {
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				elements[i][j] = new ArrayList();
				
				if ( square[i][j] != 0 ) {				
				elements[i][j].add(square[i][j]);
				}
				else {				
				elements[i][j].add(1);
				elements[i][j].add(2);
				elements[i][j].add(3);
				elements[i][j].add(4);
				}
			}
		}
	
	}
	
	private void displayArrList() {
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				System.out.println("Arr List at " + i + " , " + j + " -> " + elements[i][j]);
			}
		}
	}
	
	private void trim() {
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				
				int elem = square[i][j] ;
				
				if ( elem != 0 ) {
					
					Integer i1 = new Integer(elem);
					
					// Remove all elements except the given element from this arrayList
					int iLocal = 0 ;
					int sizeLocal = elements[i][j].size() ;
					
					for ( int temp = 0 ; temp < sizeLocal ; temp++ ) {
						
						if ( (int)elements[i][j].get(iLocal) == i1 ) {
							iLocal++ ;							
						}
						else {
							elements[i][j].remove(iLocal);	
						}
					}
					
					// Remove the 'elem' from this collumn
					for ( int l = 1 ; l <= 4 ; l++ ) {
						if ( l != i ) {
							if ( elements[l][j].contains(i1) ) {
								
								conti = true ;
								elements[l][j].remove(i1);

							}

						}
					}
					// Remove the 'elem' from this row					
					for ( int k = 1 ; k <= 4 ; k++ ) {
						if ( k != j ) {
							if ( elements[i][k].contains(i1) ) {
								
								conti = true ;
								elements[i][k].remove(i1);
							}
						}
					}
					
					
					removeElemFromThisBox : {
						
					int xMin = 0 , xMax = 0 ;
					int yMin = 0 , yMax = 0 ;
						
					if ( j % 2 == 1 ) {

						xMin = j ;
						xMax = j + 1 ;
						
					}
					
					if ( j % 2 == 0 ) {

						xMin = j - 1 ;
						xMax = j ;
					}
					
					if ( i % 2 == 1 ) {

						yMin = i ;
						yMax = i + 1 ;
						
					}
					
					if ( i % 2 == 0 ) {

						yMin = i - 1 ;
						yMax = i ;
					}
					
					for ( int tempY = yMin ; tempY <= yMax ; tempY++ ) {
						for ( int tempX = xMin ; tempX <= xMax ; tempX++ ) {

							if ( ! ( tempY == i && tempX == j ) ) {
								if (elements[tempY][tempX].contains(i1) ) {
								conti = true ;
								elements[tempY][tempX].remove(i1);
								}
							}
						}

					}
					}
					
					
					
				}
			}	
		}		
	}
	
	private ArrayList[][] trim( int[][] sqr , ArrayList[][] elementsTemp ) {
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				
				int elem = sqr[i][j] ;
												
				if ( elem != 0 ) {
					
					Integer i1 = new Integer(elem);		
					
					// Remove all elements except the given element from this arrayList
					
					int iLocal = 0 ;
					int sizeLocal = elements[i][j].size() ;
					for ( int temp = 0 ; temp< sizeLocal ; temp++ ) {
						if ( (int)elements[i][j].get(iLocal) == i1 ) {
							iLocal++ ;							
						}
						else {
							elements[i][j].remove(iLocal);	
						}
					}
					
					// Remove the 'elem' from this collumn
					for ( int l = 1 ; l <= 4 ; l++ ) {
						if ( l != i ) {
							if ( elementsTemp[l][j].contains(i1) ) {
								
								conti = true ;
								elementsTemp[l][j].remove(i1);

							}

						}
					}
					// Remove the 'elem' from this row					
					for ( int k = 1 ; k <= 4 ; k++ ) {
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
						
					if ( j % 2 == 1 ) {

						xMin = j ;
						xMax = j + 1 ;
						
					}
					
					if ( j % 2 == 0 ) {

						xMin = j - 1 ;
						xMax = j ;
					}
					
					if ( i % 2 == 1 ) {

						yMin = i ;
						yMax = i + 1 ;
						
					}
					
					if ( i % 2 == 0 ) {

						yMin = i - 1 ;
						yMax = i ;
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
	
	private void updateSqr() {
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				
				if ( ( elements[i][j].size() == 1 && square[i][j] == 0 )) {
					
					square[i][j] = (int)elements[i][j].get(0);
				}
			}
		}
		
	}
	
	private int[][] updateSqr( int[][] squareTemp , ArrayList[][] elementsTemp ) {
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				
				if ( ( elementsTemp[i][j].size() == 1 && squareTemp[i][j] == 0 )) {
					
					squareTemp[i][j] = (int)elementsTemp[i][j].get(0);
				}
			}
		}
		
		return squareTemp ;
		
	}
	
	private void chkForCompletion() {
		
		done = true ;
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				if ( square[i][j] == 0 ) {
					done = false ;
					break ;
				}
			}
			if ( !done ) {
				break ;
				
			}
			
		}
		System.out.println("Su-Do-Ku complete ? " + done );
		
	}
	
	private boolean chkForCompletion( int[][] sqr) {
		
		done = true ;
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
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
	
	private boolean chkIfCorrect( int[][] sqr ) {
		
		for ( int i = 1 ; i <= 4 ; i++ ) {
		for ( int j = 1 ; j < 4 ; j++ ) {
			for ( int k = ( j + 1 ) ; k <= 4 ; k++ ) {
				dupFound = false ;
				if ( sqr[i][j] == sqr[i][k] ) {
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
			return ( ! dupFound )  ;
		}
		
		for ( int a = 1 ; a <= 4 ; a++ ) {
			for ( int b = 1 ; b < 4 ; b++ ) {
				for ( int c = ( b + 1 ) ; c <= 4 ; c++ ) {
					if ( sqr[b][a] == sqr[c][a] ) {
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
			return ( ! dupFound )  ;
		}
		
		checkForDupElementsInTheBox : {
			

		
		for ( int i =1 ; i <= 4 ; i++ ) {
			for ( int j = 1 ; j <= 4 ; j++ ) {
				
				int xMin = 0 , xMax = 0 ;
				int yMin = 0 , yMax = 0 ;
				
				if ( j % 2 == 1 ) {

					xMin = j ;
					xMax = j + 1 ;
					
				}
				
				if ( j % 2 == 0 ) {

					xMin = j - 1 ;
					xMax = j ;
				}
				
				if ( i % 2 == 1 ) {

					yMin = i ;
					yMax = i + 1 ;
					
				}
				
				if ( i % 2 == 0 ) {

					yMin = i - 1 ;
					yMax = i ;
				}
				
				for ( int tempY = yMin ; tempY <= yMax ; tempY++ ) {
					for ( int tempX = xMin ; tempX <= xMax ; tempX++ ) {

						if ( ! ( tempY == i && tempX == j ) ) {
						if ( sqr[i][j] == sqr[tempY][tempX]) {
							System.out.println("Duplicate found." );
							dupFound = true ;
							return ( ! dupFound )  ;
						}
						}
					}
				}
				
			}
		}

		}
		
		return ( ! dupFound ) ;
		
	}

}
