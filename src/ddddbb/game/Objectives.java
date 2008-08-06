package ddddbb.game;

import ddddbb.comb.DOp;

public enum Objectives {
//	Trivial( 
//			new int[][] {
//					new int[] { 1,0,0,0 },
//					new int[] { 0,0,0,0 }
//			},
//			new int[][][]  { 
//					new int[][] { new int[] {  1,0,0,0 } },
//					new int[][] { new int[] { 0,0,-1,-2 } },
//			}
//	),
	Bar(
			new int[][] {
					new int[] { 2,0,0,0 },
					new int[] { 1,0,0,0 },
					new int[] { 0,0,0,0 }
			},
			new int[][][]  { 
					new int[][] {
							new int[] { -1, 0, 0 ,0 },
							new int[] { -1, 1, 0 , 0}
					},
					new int[][] { new int[] {1,0,0,0} }
			}
	),
	Two_Cuboids("Two Cuboids",
			DOp.create4dCube(2),
			new int[][][] {
				new int[][] {
						new int[] { 0,0,0,0 },
						new int[] { 1,0,0,0 },
						new int[] { 0,1,0,0 },
						new int[] { 1,1,0,0 },
						new int[] { 0,0,1,0 },
						new int[] { 1,0,1,0 },
						new int[] { 0,1,1,0 },
						new int[] { 1,1,1,0 },
				},
				DOp.trans(new int[][] {
						new int[] { 0,0,0,1 },
						new int[] { 1,0,0,1 },
						new int[] { 0,1,0,1 },
						new int[] { 1,1,0,1 },
						new int[] { 0,0,1,1 },
						new int[] { 1,0,1,1 },
						new int[] { 0,1,1,1 },
						new int[] { 1,1,1,1 },
				}, new int[] {3,0,0,-1}),
		}				
	),
	Worm(
			new int[][] {
					new int[] { 0,0,0,0 },
					new int[] { 1,0,0,0 },
					new int[] { 1,1,0,0 },
					new int[] { 1,1,1,0 },
					new int[] { 1,1,1,-1 },
			},
			new int[][][] {
					new int[][] { new int[] { 1,1,0,0 } },
					new int[][] { new int[] { 0,0,0,0 } },
					new int[][] { new int[] { 2,0,0,0 } },
					new int[][] { new int[] { 0,2,0,0 } },
					new int[][] { new int[] { 2,2,0,0 } },
			}
	),
	Star(
			DOp.create4dStar(),
			new int[][][] {
				new int[][] { new int[] { 0,0,0,0 }},
				new int[][] { new int[] { 2,0,0,0 }},
				new int[][] { new int[] { -2,0,0,0 }},
				new int[][] { new int[] { 0,2,0,0 }},
				new int[][] { new int[] { 0,-2,0,0 }},
				new int[][] { new int[] { 0,0,2,0 }},
				new int[][] { new int[] { 0,0,-2,0 }},
				new int[][] { new int[] { 0,0,0,2 }},
				new int[][] { new int[] { 0,0,0,-2 }},
			}
	),
	Twirled_Pieces("Twirled Pieces",
			DOp.create4dCube(2),
			new int[][][] {
				DOp.trans(DOp.rot(new int[][] {
						new int[] { 0,0,0,0 },
						new int[] { 0,0,1,0 },
						new int[] { 1,0,1,0 },
						new int[] { 1,1,1,0 },
						new int[] { 1,1,1,1 },
						new int[] { 1,0,1,1 },
						new int[] { 0,0,1,1 },
						new int[] { 0,0,0,1 },
				},0,2),new int[] { 1,0,0,0 }),
				DOp.trans(new int[][] {
						new int[] { 1,0,0,0 },
						new int[] { 1,1,0,0 },
						new int[] { 0,1,0,0 },
						new int[] { 0,1,1,0 },
						new int[] { 0,1,1,1 },
						new int[] { 0,1,0,1 },
						new int[] { 1,1,0,1 },
						new int[] { 1,0,0,1 },
				},new int[] {3,0,0,0})
			}
			
	),
	D3Box(
			"3D Box",
			new int[][] {
					new int[] { 0,0,0,0 },
					new int[] { 1,0,0,0 },
					new int[] { 2,0,0,0 },
					new int[] { 0,1,0,0 },
					new int[] { 1,1,0,0 },
					new int[] { 2,1,0,0 },
					new int[] { 0,2,0,0 },
					new int[] { 1,2,0,0 },
					new int[] { 2,2,0,0 },

					new int[] { 0,0,1,0 },
					new int[] { 1,0,1,0 },
					new int[] { 2,0,1,0 },
					new int[] { 0,1,1,0 },
					new int[] { 1,1,1,0 },
					new int[] { 2,1,1,0 },
					new int[] { 0,2,1,0 },
					new int[] { 1,2,1,0 },
					new int[] { 2,2,1,0 },

					new int[] { 0,0,2,0 },
					new int[] { 1,0,2,0 },
					new int[] { 2,0,2,0 },
					new int[] { 0,1,2,0 },
					new int[] { 1,1,2,0 },
					new int[] { 2,1,2,0 },
					new int[] { 0,2,2,0 },
					new int[] { 1,2,2,0 },
					new int[] { 2,2,2,0 },
			},
			new int[][][] {
					new int[][] {
							new int[] { 0,0,0,0 },
							new int[] { 1,0,0,0 },
							new int[] { 2,0,0,0 },
							new int[] { 0,1,0,0 },
							new int[] { 1,1,0,0 },
							new int[] { 2,1,0,0 },
							new int[] { 0,2,0,0 },
							new int[] { 1,2,0,0 },
							new int[] { 2,2,0,0 },

							new int[] { 0,0,1,0 },
							new int[] { 1,0,1,0 },
							new int[] { 2,0,1,0 },
							new int[] { 0,1,1,0 },
							
							new int[] { 2,1,1,0 },
							new int[] { 0,2,1,0 },
							new int[] { 1,2,1,0 },
							new int[] { 2,2,1,0 },

							new int[] { 0,0,2,0 },
							new int[] { 1,0,2,0 },
							new int[] { 2,0,2,0 },
							new int[] { 0,1,2,0 },
							new int[] { 1,1,2,0 },
							new int[] { 2,1,2,0 },
							new int[] { 0,2,2,0 },
							new int[] { 1,2,2,0 },
							new int[] { 2,2,2,0 },
					},
					new int[][] {
							new int[] { 4,1,1,0 },
					}
			}
	),
	Mirrored_Worms("Mirrored Worms",
			new int[][] {
					new int[] { 0,0,0,0 },
					new int[] { 1,0,0,0 },
					new int[] { 1,1,0,0 },
					new int[] { 1,1,1,0 },
					new int[] { 1,1,1,-1 },
					new int[] { 2,0,0,0 },
					new int[] { 3,0,0,0 },
					new int[] { 3,1,0,0 },
					new int[] { 3,1,1,0 },
					new int[] { 3,1,1,-1 },
			},
					
			new int[][][] {
					new int[][] {
							new int[] { 0,0,0,0 },
							new int[] {-1,0,0,0 },
							new int[] {-1,-1,0,0 },
							new int[] {-1,-1,-1,0},
							new int[] {-1,-1,-1,1},
					},
					new int[][] {
							new int[] { 2,0,0,0 },
							new int[] { 3,0,0,0 },
							new int[] { 3,1,0,0 },
							new int[] { 3,1,1,0 },
							new int[] { 3,1,1,-1 },
					},
			}
	),
	Plug(
			DOp.create4dCube(2),
			new int[][][] {
				DOp.trans(DOp.rot(DOp.rot(new int[][] {
						new int[] { 0,0,0,0 },
						new int[] { 1,0,0,0 },
						new int[] { 0,1,0,0 },
						new int[] { 1,1,0,0 },
						new int[] { 0,0,1,0 },
						new int[] { 1,0,1,0 },
						new int[] { 0,1,1,0 },
						new int[] { 1,1,1,0 },
						new int[] { 0,0,0,1 },
				},0,3),0,3),new int[] {1,0,0,1}),
				DOp.trans( new int[][] {
						new int[] { 1,0,0,1 },
						new int[] { 0,1,0,1 },
						new int[] { 1,1,0,1 },
						new int[] { 0,0,1,1 },
						new int[] { 1,0,1,1 },
						new int[] { 0,1,1,1 },
						new int[] { 1,1,1,1 },
				},new int[] {3,0,0,0}),
		}				
	),
	TwoRings("Two Rings",			
			new int[][] {
					new int[] {0,0,0,0},
					new int[] {1,0,0,0},
					new int[] {2,0,0,0},
					new int[] {2,1,0,0},
					new int[] {2,2,0,0},
					new int[] {1,2,0,0},
					new int[] {0,2,0,0},
					new int[] {0,1,0,0},
					
					new int[] {0,0,0,1},
					new int[] {1,0,0,1},
					new int[] {2,0,0,1},
					new int[] {2,1,0,1},
					new int[] {2,2,0,1},
					new int[] {1,2,0,1},
					new int[] {0,2,0,1},
					new int[] {0,1,0,1}
			},
			new int[][][] {
					new int[][] {
							new int[] {0,0,0,0},
							new int[] {1,0,0,0},
							new int[] {2,0,0,0},
							new int[] {2,1,0,0},
							new int[] {2,2,0,0},
							new int[] {1,2,0,0},
							new int[] {0,2,0,0},
							new int[] {0,1,0,0}
					},
					new int[][] {
							new int[] {1,1,-1,0},
							new int[] {1,1,0,0},
							new int[] {1,1,1,0},
							new int[] {2,1,1,0},
							new int[] {3,1,1,0},
							new int[] {3,1,0,0},
							new int[] {3,1,-1,0},
							new int[] {2,1,-1,0},
					}
			}
	),
	
	Clamp(
			"4D Clamp",
			new int[][] {
					new int[] { 0,0,0,0 },
					new int[] { 1,0,0,0 },
					new int[] { 2,0,0,0 },
					new int[] { 0,1,0,0 },
					new int[] { 1,1,0,0 },
					new int[] { 2,1,0,0 },
					new int[] { 0,2,0,0 },
					new int[] { 1,2,0,0 },
					new int[] { 2,2,0,0 },

					new int[] { 0,0,1,0 },
					new int[] { 1,0,1,0 },
					new int[] { 2,0,1,0 },
					new int[] { 0,1,1,0 },
					new int[] { 1,1,1,0 },
					new int[] { 2,1,1,0 },
					new int[] { 0,2,1,0 },
					new int[] { 1,2,1,0 },
					new int[] { 2,2,1,0 },

					new int[] { 0,0,2,0 },
					new int[] { 1,0,2,0 },
					new int[] { 2,0,2,0 },
					new int[] { 0,1,2,0 },
					new int[] { 1,1,2,0 },
					new int[] { 2,1,2,0 },
					new int[] { 0,2,2,0 },
					new int[] { 1,2,2,0 },
					new int[] { 2,2,2,0 },
			},
			new int[][][] {
					new int[][] {
							new int[] { 0,0,0,0 },
							new int[] { 1,0,0,0 },
							new int[] { 2,0,0,0 },
							new int[] { 0,1,0,0 },
							new int[] { 1,1,0,0 },
							new int[] { 2,1,0,0 },
							new int[] { 0,2,0,0 },
							new int[] { 1,2,0,0 },
							new int[] { 2,2,0,0 },

							new int[] { 0,0,1,0 },

							new int[] { 2,0,1,0 },
							new int[] { 0,1,1,0 },
							new int[] { 1,1,1,0 }, 
							new int[] { 2,1,1,0 },
							new int[] { 0,2,1,0 },

							new int[] { 2,2,1,0 },

							new int[] { 0,0,2,0 },
							new int[] { 1,0,2,0 },
							new int[] { 2,0,2,0 },
							new int[] { 0,1,2,0 },
							new int[] { 1,1,2,0 },
							new int[] { 2,1,2,0 },
							new int[] { 0,2,2,0 },
							new int[] { 1,2,2,0 },
							new int[] { 2,2,2,0 },
					},
					new int[][] {
							new int[] { 4,0,1,0 },
							new int[] { 4,2,1,0 },
					}
			}
	),
	TriClamp(
			"Triclamp",
			new int[][] {
					new int[] { 0,0,0,0 },
					new int[] { 1,0,0,0 },
					new int[] { 2,0,0,0 },
					new int[] { 0,1,0,0 },
					new int[] { 1,1,0,0 },
					new int[] { 2,1,0,0 },
					new int[] { 0,2,0,0 },
					new int[] { 1,2,0,0 },
					new int[] { 2,2,0,0 },

					new int[] { 0,0,1,0 },
					new int[] { 1,0,1,0 },
					new int[] { 2,0,1,0 },
					new int[] { 0,1,1,0 },
					new int[] { 1,1,1,0 },
					new int[] { 2,1,1,0 },
					new int[] { 0,2,1,0 },
					new int[] { 1,2,1,0 },
					new int[] { 2,2,1,0 },

					new int[] { 0,0,2,0 },
					new int[] { 1,0,2,0 },
					new int[] { 2,0,2,0 },
					new int[] { 0,1,2,0 },
					new int[] { 1,1,2,0 },
					new int[] { 2,1,2,0 },
					new int[] { 0,2,2,0 },
					new int[] { 1,2,2,0 },
					new int[] { 2,2,2,0 },
			},
			new int[][][] {
					new int[][] {
							new int[] { 0,0,0,0 },
							new int[] { 1,0,0,0 },
							new int[] { 2,0,0,0 },
							new int[] { 0,1,0,0 },

							new int[] { 2,1,0,0 },
							new int[] { 0,2,0,0 },
							new int[] { 1,2,0,0 },
							new int[] { 2,2,0,0 },

							new int[] { 0,0,1,0 },

							new int[] { 2,0,1,0 },
							new int[] { 1,1,1,0 }, 

							new int[] { 0,2,1,0 },

							new int[] { 2,2,1,0 },

							new int[] { 0,0,2,0 },
							new int[] { 1,0,2,0 },
							new int[] { 2,0,2,0 },
							new int[] { 0,1,2,0 },
							new int[] { 2,1,2,0 },
							new int[] { 0,2,2,0 },
							new int[] { 1,2,2,0 },
							new int[] { 2,2,2,0 },
					},
					new int[][] {
							new int[] { 5,1,0,0 },
							new int[] { 5,0,1,0 },
							new int[] { 5,2,1,0 },
							new int[] { 6,1,1,0 },
							new int[] { 4,1,1,0 },
							new int[] { 5,1,2,0 },
					}
			}
	),
	Exam("4 Tesseract Pieces",
			DOp.create4dCube(2),
			new int[][][] {
				new int[][] {
						new int[] { 0, 1, 0, 0 },
						new int[] { 0, 0, 0, 0 },
						new int[] { 1, 0, 0, 0 },
						new int[] { 1, 0, 1, 0 },
				},
				DOp.trans(DOp.rot(new int[][] {
						new int[] { 1, 0, 0, 1 },
						new int[] { 1, 0, 1, 1 },
						new int[] { 0, 0, 1, 1 },
						new int[] { 0, 0, 1, 0 },
				},1,3),new int[] {3,1,-1,0}),
				DOp.trans(DOp.rot(DOp.rot(new int[][] {
						new int[] { 1, 1, 0, 0 },
						new int[] { 1, 1, 0, 1 },
						new int[] { 0, 1, 0, 1 },
						new int[] { 0, 0, 0, 1 },
				},2,1),2,1), new int[] {0,4,0,-1}),
				DOp.trans(DOp.rot(new int[][] {
						new int[] { 0, 1, 1, 0 },
						new int[] { 0, 1, 1, 1 },
						new int[] { 1, 1, 1, 1 },
						new int[] { 1, 1, 1, 0 },
				},3,1),new int[] {3,3,-1,1}),

		}
	)
	;
	private Objectives(String _name,int[][] _goal,int[][][] _compounds) {
		this(_goal,_compounds);
		name=_name;
	}
	private Objectives(int[][] _goal,int[][][] _compounds) {
		goal=_goal;
		compounds =_compounds;			
	}
	public int[][] goal;
	public int[][][] compounds;
	private String name;
	public String toString() {
		if (name!=null) { return name; }
		return super.toString();
	}

}
