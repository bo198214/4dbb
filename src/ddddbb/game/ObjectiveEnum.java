package ddddbb.game;

import java.lang.reflect.Field;

import ddddbb.comb.DOp;

public class ObjectiveEnum {
	//	Trivial( 
//			new int[][] {
//					new int[] { 1,0,0,0 },
//					new int[] { 0,0,0,0 }
//			},
//			new int[][][]  { 
//					new int[][] { new int[] {  1,0,0,0 } },
//					new int[][] { new int[] { 0,0,-1,-2 } },
//			}
//	);
	public static Objective Bar = new Objective("Bar",
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
	);
	public static Objective Two_Cuboids = new Objective("Two Cuboids",
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
	);
	public static Objective Worm = new Objective("Worm",
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
	);
	public static Objective Star = new Objective("Star",
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
	);
	public static Objective Twirled_Pieces = new Objective("Twirled Pieces",
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
			
	);
	public static Objective D3Box = new Objective(
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
	);
	public static Objective Mirrored_Worms = new Objective("Mirrored Worms",
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
	);
	public static Objective Plug = new Objective("Plug",
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
	);
	public static Objective TwoRings = new Objective("Two Rings",			
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
	);
	
	public static Objective Clamp = new Objective(
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
	);
	public static Objective TriClamp = new Objective(
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
	);
	public static Objective Exam = new Objective("4 Tesseract Pieces",
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
	);
	
	public static Objective[] values() {
		Class<ObjectiveEnum> c = ObjectiveEnum.class;
		Field[] fields = c.getDeclaredFields();
		Objective[] res = new Objective[fields.length];
		
		for (int i=0;i<fields.length;i++) {
			try {
				res[i] = (Objective) fields[i].get(null);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return res;
	}
}
