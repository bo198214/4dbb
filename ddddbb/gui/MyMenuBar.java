package ddddbb.gui;

import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
//import java.awt.print.PrinterException;
//import java.awt.print.PrinterJob;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;

import ddddbb.game.Opt;
import ddddbb.math.Param;


/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MyMenuBar extends JMenuBar {
	private static final long serialVersionUID = -8486348229885411290L;

	private JMenu fileMenu = null;

	private JMenu screenMenu = null;
	
	private JMenuItem exitMenuItem = null;

	private JMenuItem saveMenuItem = null;

	private JMenuItem jLoadSceneMenuItem = null;

	private JMenuItem jSaveSceneMenuItem = null;

	private JMenuItem jSavePsMenuItem = null;

	private JMenu objectivesMenu = null;

	private JMenu viewMenu = null;

	private JMenuItem garbageCollectMenuItem = null;

//	private JMenuItem printMenuItem = null;

	protected JMenuItem savePdfMenuItem = null;

	private JFrame selectFileFrame = null;  //  @jve:decl-index=0:visual-constraint="293,84"

	protected JMenuItem savePngMenuItem = null;

	protected JMenuItem saveJpgMenuItem = null;
	private JMenu projMenu;

	private JMenu saveCanvasMenu = null;

	public MyMenuBar() {
		super();
		initialize();
	}
	
	private void initialize() {
		add(getFileMenu());
		add(getViewMenu());
		add(getProjMenu());
		add(getScreenMenu());
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getFileMenu() {
		if (fileMenu == null) {
			fileMenu = new JMenu();
			fileMenu.setText("File");
			fileMenu.add(getObjectivesMenu());
			fileMenu.add(getSaveMenuItem());
			fileMenu.add(getJLoadSceneMenuItem());
			fileMenu.add(getJSaveSceneMenuItem());
			fileMenu.add(getSaveCanvasMenu());
			
//			fileMenu.add(getPrintMenuItem());
			fileMenu.add(getGarbageCollectMenuItem());
			fileMenu.add(getExitMenuItem());
		}
		return fileMenu;
	}

	/**
	 * This method initializes jMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	public JMenu getScreenMenu() {
		if (screenMenu == null) {
			screenMenu = new JMenu();
			screenMenu.setText("Screen");
			Opt.showedScreen.addAsRadioButtonMenuItems(screenMenu);
		}
		return screenMenu;
	}
	
	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getExitMenuItem() {
		if (exitMenuItem == null) {
			exitMenuItem = new JMenuItem();
			exitMenuItem.setText("Exit");
			exitMenuItem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					System.exit(0);
				}
			});
		}
		return exitMenuItem;
	}

	/**
	 * This method initializes jMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSaveMenuItem() {
		if (saveMenuItem == null) {
			saveMenuItem = new JMenuItem();
			saveMenuItem.setText("Save");
			saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
					Event.CTRL_MASK, true));
			saveMenuItem.setEnabled(false);
		}
		return saveMenuItem;
	}

	/**
	 * This method initializes jLoadSceneMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJLoadSceneMenuItem() {
		if (jLoadSceneMenuItem == null) {
			jLoadSceneMenuItem = new JMenuItem();
			jLoadSceneMenuItem.setText("Load scene ...");
			jLoadSceneMenuItem.setEnabled(false);
		}
		return jLoadSceneMenuItem;
	}

	/**
	 * This method initializes jSaveSceneMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJSaveSceneMenuItem() {
		if (jSaveSceneMenuItem == null) {
			jSaveSceneMenuItem = new JMenuItem();
			jSaveSceneMenuItem.setText("Save scene ...");
			jSaveSceneMenuItem.setEnabled(false);
		}
		return jSaveSceneMenuItem;
	}

	/**
	 * This method initializes jSavePsMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getJSavePsMenuItem() {
		if (jSavePsMenuItem == null) {
			jSavePsMenuItem = new JMenuItem();
			jSavePsMenuItem.setText(".eps");
			
			jSavePsMenuItem.setToolTipText("Not yet available.");
			jSavePsMenuItem.setEnabled(false);
		}
		return jSavePsMenuItem;
	}

	/**
	 * This method initializes objectivesMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getObjectivesMenu() {
		if (objectivesMenu == null) {
			objectivesMenu = new JMenu("objectives");
			Opt.objectives.addAsMenuItems(objectivesMenu);
		}
		return objectivesMenu;
	}

	/**
	 * This method initializes viewMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getViewMenu() {
		if (viewMenu == null) {
			viewMenu = new JMenu();
			viewMenu.setText("View");
			Opt.viewType.addAsRadioButtonMenuItems(viewMenu);
			viewMenu.addSeparator();
			Param.occlusion4dAllowance.addAsRadioButtonMenuItems(viewMenu);
			viewMenu.addSeparator();
			Opt.drawTetrahedral.addAsCheckBoxMenuItem(viewMenu);
			//Opt.drawTrihedral.addAsCheckBoxMenuItem(viewMenu);
			Param.showInternalFaces.addAsCheckBoxMenuItem(viewMenu);
			Opt.antiAliased.addAsCheckBoxMenuItem(viewMenu);
			viewMenu.addSeparator();
			Opt.soundOn.addAsCheckBoxMenuItem(viewMenu);
			Opt.debug.addAsCheckBoxMenuItem(viewMenu);
		}
		return viewMenu;
	}

	/**
	 * This method initializes garbageCollectMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getGarbageCollectMenuItem() {
		if (garbageCollectMenuItem == null) {
			garbageCollectMenuItem = new JMenuItem();
			garbageCollectMenuItem.setText("collect garbage");
			garbageCollectMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					System.gc();
				}
			});
		}
		return garbageCollectMenuItem;
	}

//	/**
//	 * This method initializes printMenuItem	
//	 * 	
//	 * @return javax.swing.JMenuItem	
//	 */
//	private JMenuItem getPrintMenuItem() {
//		if (printMenuItem == null) {
//			printMenuItem = new JMenuItem();
//			printMenuItem.setText("print canvas");
//			printMenuItem.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					PrinterJob printJob = PrinterJob.getPrinterJob();
//			        printJob.setPrintable(Opt.viewScreen);
//			        printJob.printDialog();
//			        try {
//						printJob.print();
//					} catch (PrinterException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			});
//		}
//		return printMenuItem;
//	}

	/**
	 * This method initializes savePdfMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSavePdfMenuItem() {
		if (savePdfMenuItem == null) {
			savePdfMenuItem = new JMenuItem();
			savePdfMenuItem.setText(".pdf");
			savePdfMenuItem.setToolTipText("Not enabled.");
			savePdfMenuItem.setEnabled(false);
//			savePdfMenuItem.setToolTipText("Only for parallel and crossed eye mode availbable.");
//			savePdfMenuItem.addActionListener(new java.awt.event.ActionListener() {
//				public void actionPerformed(java.awt.event.ActionEvent e) {
//					try {
//						FileDialog d = new FileDialog(getSelectFileFrame(),"PDF File",FileDialog.SAVE);
//						d.setVisible(true);
//						String fname = d.getFile();
//						//d.setDirectory("xyz");
//						if (  fname == null ) { return; }
//						Point2d min = new Point2d();
//						Point2d max = new Point2d();
//						Opt.scene.BoundaryCuboid2d(min,max);
//						System.out.println(min.x1);
//						System.out.println(min.x2);
//						System.out.println(max.x1);
//						System.out.println(max.x2);
//						PDFGraphics g2 = new PDFGraphics(new File(d.getDirectory(),fname));
//						D3Graphics g3 = new ParallelEyedGraphics(g2,Opt.scene.camera3d);
//						Opt.scene.paint(g3);
//						if (Opt.drawTrihedral.isSelected()) {
//							g3.drawTrihedral();
//						}
//						if (Opt.drawTetrahedral.isSelected()) {
//							new D4Graphics(g3,Opt.scene.camera4d).drawTetrahedral();
//						}
//						g2.close();
//					} catch (DocumentException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					} catch (IOException e1) {
//						// TODO Auto-generated catch block
//						e1.printStackTrace();
//					}
//				}
//			});
//			Opt.viewType.addChangeListener(new ChangeListener() {
//				public void stateChanged(ChangeEvent e) {
//					if (
//							Opt.viewType.getSelectedObject() == Opt.ViewType.PARALLEL ||
//							Opt.viewType.getSelectedObject() == Opt.ViewType.CROSSED
//							) {
//						savePdfMenuItem.setEnabled(true);
//					}
//					else {
//						savePdfMenuItem.setEnabled(false);
//					}
//				}});
		}
		return savePdfMenuItem;
	}

	/**
	 * This method initializes selectFileFrame	
	 * 	
	 * @return javax.swing.JFrame	
	 */
	protected JFrame getSelectFileFrame() {
		if (selectFileFrame == null) {
			selectFileFrame = new JFrame();
			selectFileFrame.setSize(new java.awt.Dimension(316,144));
			
			//selectFileFrame.setContentPane(getSelectPdfFileContentPane());
		}
		return selectFileFrame;
	}

	/**
	 * This method initializes savePngMenuItem	
	 * 	
	 * @return javax.swing.JMenuItem	
	 */
	private JMenuItem getSavePngMenuItem() {
		if (savePngMenuItem == null) {
			savePngMenuItem = new JMenuItem();
			savePngMenuItem.setText(".png");
			savePngMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
				    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
				    FileFilter filter = new FileFilter() {
						@Override
						public boolean accept(File f) {
							if (f.getPath().endsWith(".png")) { return true; }
							return false;
						}

						@Override
						public String getDescription() {
							return "PNG Images";
						}};
				    chooser.setFileFilter(filter);
				    //chooser.setApproveButtonToolTipText("")
				    int returnVal = chooser.showOpenDialog(savePngMenuItem);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	try {
				    		ImageIO.write(Opt.viewScreen.buffImg,"png",chooser.getSelectedFile());
				    	} catch (IOException e1) {
				    		// TODO Auto-generated catch block
				    		e1.printStackTrace();
				    	}
				    }
				}
			});
		}
		return savePngMenuItem;
	}

	private JMenuItem getSaveJpgMenuItem() {
		if (saveJpgMenuItem == null) {
			saveJpgMenuItem = new JMenuItem();
			saveJpgMenuItem.setText(".jpg");
			saveJpgMenuItem.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFileChooser chooser = new JFileChooser();
				    chooser.setDialogType(JFileChooser.SAVE_DIALOG);
				    chooser.setDialogTitle("Save JPEG file");
				    FileFilter filter = new FileFilter() {
						@Override
						public boolean accept(File f) {
							if (f.getPath().endsWith(".jpg")) { return true; }
							return false;
						}

						@Override
						public String getDescription() {
							return "JPEG Images";
						}};
				    chooser.setFileFilter(filter);
				    //chooser.setApproveButtonToolTipText("")
				    int returnVal = chooser.showOpenDialog(saveJpgMenuItem);
				    if(returnVal == JFileChooser.APPROVE_OPTION) {
				    	try {
				    		ImageIO.write(Opt.viewScreen.buffImg,"jpg",chooser.getSelectedFile());
				    	} catch (IOException e1) {
				    		// TODO Auto-generated catch block
				    		e1.printStackTrace();
				    	}
				    }
				}
			});
		}
		return saveJpgMenuItem;
	}

	/**
	 * This method initializes saveCanvasMenu	
	 * 	
	 * @return javax.swing.JMenu	
	 */
	private JMenu getSaveCanvasMenu() {
		if (saveCanvasMenu == null) {
			saveCanvasMenu = new JMenu();
			saveCanvasMenu.setText("Save Canvas as ...");
			saveCanvasMenu.add(getSavePngMenuItem());
			saveCanvasMenu.add(getSaveJpgMenuItem());
			saveCanvasMenu.add(getSavePdfMenuItem());
			saveCanvasMenu.add(getJSavePsMenuItem());
		}
		return saveCanvasMenu;
	}
	
	private JMenu getProjMenu() {
		if(projMenu == null) {
			projMenu = new JMenu();
			projMenu.setText("Projection");
			projMenu.setBounds(0, 0, 19, 18);
			Param.perspective.addAsRadioButtonMenuItems(projMenu);
		}
		return projMenu;
	}
}
