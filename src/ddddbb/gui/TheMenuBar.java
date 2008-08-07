package ddddbb.gui;

import java.applet.Applet;
import java.awt.Event;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.prefs.BackingStoreException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.filechooser.FileFilter;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import ddddbb.game.Main;
import ddddbb.game.Objective;
import ddddbb.game.Settings;


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
public class TheMenuBar extends JMenuBar {
	private static final long serialVersionUID = -8486348229885411290L;

//	private JMenuItem printMenuItem = null;

	protected JMenuItem savePdfMenuItem = null;

	protected JMenuItem savePngMenuItem = null;

	private final Main main;

	private final JMenuItem saveObjective;

	private final JMenuItem loadObjective;
	
	private final JFileChooser objectiveFileChooser;

	public TheMenuBar(Main _main) {
		main = _main;
		{
			JMenu fileMenu = new JMenu();
			fileMenu.setText("File");
			{
				JMenuItem savePrefs = new JMenuItem();
				savePrefs.setText("Save Preferences");
				savePrefs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P,
						Event.CTRL_MASK, true));
				if (main.window instanceof Applet) {
					savePrefs.setEnabled(false);
					savePrefs.setToolTipText("Disabled in the applet version only.");
				}
				else {
					savePrefs.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							try {
								main.prefs.save();
							} catch (BackingStoreException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}});
				}
				fileMenu.add(savePrefs);
			}
			{
				objectiveFileChooser = new JFileChooser();
				FileFilter filter = new FileFilter() {
					@Override
					public boolean accept(File f) {
						if (f.getPath().endsWith(".xml")) { return true; }
						if (f.isDirectory()) { return true; }
						return false;
					}

					@Override
					public String getDescription() {
						return "XML Files";
					}
				};
				objectiveFileChooser.setFileFilter(filter);
			}

			{
				loadObjective = new JMenuItem();
				if (main.window instanceof Applet) {
					loadObjective.setEnabled(false);
					loadObjective.setToolTipText("Disabled in the applet version only.");
				}
				else {
					initLoadObjective(objectiveFileChooser);
				}
			    fileMenu.add(loadObjective);
				loadObjective.setText("Load scene (simple)...");
			}
			{
				saveObjective = new JMenuItem();
				if (main.window instanceof Applet) {
					saveObjective.setEnabled(false);
					saveObjective.setToolTipText("Disabled in the applet version only.");
				}
				else {
					initSaveObjective(objectiveFileChooser);
				}
			    fileMenu.add(saveObjective);
				saveObjective.setText("Save scene (simple)...");
			}
//			{
//			    JMenu saveCanvasMenu = new JMenu();
//				saveCanvasMenu.setText("Save screen as ...");
//				saveCanvasMenu.add(getSavePngMenuItem());
//				saveCanvasMenu.add(getSaveJpgMenuItem());
//				saveCanvasMenu.add(getSavePdfMenuItem());
//				{
//					jSavePsMenuItem = new JMenuItem();
//					jSavePsMenuItem.setText(".eps");
//					
//					jSavePsMenuItem.setToolTipText("Not yet available.");
//					jSavePsMenuItem.setEnabled(false);
//				}
//				saveCanvasMenu.add(jSavePsMenuItem);				
//				fileMenu.add(saveCanvasMenu);
//			}
			{
				getSavePngMenuItem().setText("Save Screen as PNG ...");
				fileMenu.add(getSavePngMenuItem());
			}
//			fileMenu.add(getPrintMenuItem());
			{
				JMenuItem garbageCollectMenuItem = new JMenuItem();
				garbageCollectMenuItem.setText("Collect garbage");
				garbageCollectMenuItem.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						System.gc();
					}
				});				
				fileMenu.add(garbageCollectMenuItem);
			}
			{
				JMenuItem exitMenuItem = new JMenuItem();
				exitMenuItem.setText("Exit");
				exitMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
				fileMenu.add(exitMenuItem);			
			}
			add(fileMenu);
		}
		{
			JMenu objectivesMenu = new JMenu("Objectives");
			main.ss.objectives.addAsRadioButtonMenuItems(objectivesMenu);
			add(objectivesMenu);
		}
		{
			JMenu viewMenu = new JMenu();
			viewMenu.setText("View");
			main.ss.viewType.addAsRadioButtonMenuItems(viewMenu);
			viewMenu.addSeparator();
			main.ss.occlusion4dAllowance.addAsRadioButtonMenuItems(viewMenu);
			viewMenu.addSeparator();
			main.ss.drawTetrahedral.addAsCheckBoxMenuItem(viewMenu);
			//opt.drawTrihedral.addAsCheckBoxMenuItem(viewMenu);
			main.ss.showInternalFaces.addAsCheckBoxMenuItem(viewMenu);
			main.ss.antiAliased.addAsCheckBoxMenuItem(viewMenu);
			viewMenu.addSeparator();
			Settings.soundOn.addAsCheckBoxMenuItem(viewMenu);
//			Main.debug.addAsCheckBoxMenuItem(viewMenu);
			add(viewMenu);
		}
		{
			JMenu projMenu = new JMenu();
			projMenu.setText("Projection");
			projMenu.setBounds(0, 0, 19, 18);
			main.ss.perspective.addAsRadioButtonMenuItems(projMenu);
			projMenu.addSeparator();
			main.ss.orientation3d.addAsRadioButtonMenuItems(projMenu);
			projMenu.addSeparator();
			main.ss.orientation4d.addAsRadioButtonMenuItems(projMenu);
			add(projMenu);
		}
		{
			JMenu screenMenu = new JMenu();
			screenMenu.setText("Screen/Help");
			main.showedScreen.addAsRadioButtonMenuItems(screenMenu);			
			add(screenMenu);
		}
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
//			        printJob.setPrintable(opt.viewScreen);
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
//			savePdfMenuItem.setToolTipText("Only for parallel and cromain.ssed eye mode availbable.");
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
//						opt.scene.BoundaryCuboid2d(min,max);
//						System.out.println(min.x1);
//						System.out.println(min.x2);
//						System.out.println(max.x1);
//						System.out.println(max.x2);
//						PDFGraphics g2 = new PDFGraphics(new File(d.getDirectory(),fname));
//						D3Graphics g3 = new ParallelEyedGraphics(g2,opt.scene.camera3d);
//						opt.scene.paint(g3);
//						if (opt.drawTrihedral.isSelected()) {
//							g3.drawTrihedral();
//						}
//						if (opt.drawTetrahedral.isSelected()) {
//							new D4Graphics(g3,opt.scene.camera4d).drawTetrahedral();
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
//			opt.viewType.addChangeListener(new ChangeListener() {
//				public void stateChanged(ChangeEvent e) {
//					if (
//							opt.viewType.getSelectedObject() == opt.ViewType.PARALLEL ||
//							opt.viewType.getSelectedObject() == opt.ViewType.CROSSED
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

	private JFileChooser savePngFileChooser;
	private JMenuItem getSavePngMenuItem() {
		if (savePngMenuItem != null) return savePngMenuItem;
		savePngMenuItem = new JMenuItem();
		savePngMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
				Event.CTRL_MASK, true));
		savePngMenuItem.setText(".png");
		savePngFileChooser = new JFileChooser();
		savePngFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
		FileFilter filter = new FileFilter() {
			@Override
			public boolean accept(File f) {
				if (f.getPath().endsWith(".png")) { return true; }
				if (f.isDirectory()) { return true; }
				return false;
			}

			@Override
			public String getDescription() {
				return "PNG Images";
			}
		};
		savePngFileChooser.setFileFilter(filter);
		
		savePngMenuItem.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
					//chooser.setApproveButtonToolTipText("")
					int returnVal = savePngFileChooser.showSaveDialog(savePngMenuItem);
					if(returnVal == JFileChooser.APPROVE_OPTION) {
						try {
							ImageIO.write(main.viewScreen.buffImg,"png",savePngFileChooser.getSelectedFile());
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
			}
		});
		return savePngMenuItem;
	}

	private void initSaveObjective(final JFileChooser objectiveFileChooser) {
		saveObjective.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				objectiveFileChooser.setDialogType(JFileChooser.SAVE_DIALOG);
				objectiveFileChooser.setDialogTitle("Save current objective to file"); 
				int returnVal = objectiveFileChooser.showSaveDialog(saveObjective);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						main.scene.asObjective().save(objectiveFileChooser.getSelectedFile());
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}});
		
		return;
	}
	
	private void initLoadObjective(final JFileChooser objectiveFileChooser) {
		loadObjective.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				objectiveFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
				objectiveFileChooser.setDialogTitle("Load objective from file");
				int returnVal = objectiveFileChooser.showOpenDialog(loadObjective);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
					try {
						Objective objective = new Objective(objectiveFileChooser.getSelectedFile());
						main.scene.changeObjective(objective);
						main.goalScene.setCompounds(new int[][][] {objective.goal});
					} catch (ParserConfigurationException e1) {
						e1.printStackTrace();
					} catch (SAXException e1) {
						e1.printStackTrace();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
				}
			}});
	}
}
