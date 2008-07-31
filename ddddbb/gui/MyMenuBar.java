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

import ddddbb.game.Main;
import ddddbb.game.Objectives;
import ddddbb.game.SimpleSwitches;
import ddddbb.game.Main.PerspectiveEnum;
import ddddbb.game.Main.ShowedScreenEnum;
import ddddbb.gen.IntModel;
import ddddbb.math.Camera4d;


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

	private final Main main;
	
	public MyMenuBar(Main _main) {
		main = _main;
		{
			fileMenu = new JMenu();
			fileMenu.setText("File");
			{
				saveMenuItem = new JMenuItem();
				saveMenuItem.setText("Save");
				saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S,
						Event.CTRL_MASK, true));
				saveMenuItem.setEnabled(false);				
			}
			fileMenu.add(saveMenuItem);
			{
				jLoadSceneMenuItem = new JMenuItem();
				jLoadSceneMenuItem.setText("Load scene ...");
				jLoadSceneMenuItem.setEnabled(false);
			}
			fileMenu.add(jLoadSceneMenuItem);
			{
				jSaveSceneMenuItem = new JMenuItem();
				jSaveSceneMenuItem.setText("Save scene ...");
				jSaveSceneMenuItem.setEnabled(false);
			}
			fileMenu.add(jSaveSceneMenuItem);
			{
				saveCanvasMenu = new JMenu();
				saveCanvasMenu.setText("Save Canvas as ...");
				saveCanvasMenu.add(getSavePngMenuItem());
				saveCanvasMenu.add(getSaveJpgMenuItem());
				saveCanvasMenu.add(getSavePdfMenuItem());
				{
					jSavePsMenuItem = new JMenuItem();
					jSavePsMenuItem.setText(".eps");
					
					jSavePsMenuItem.setToolTipText("Not yet available.");
					jSavePsMenuItem.setEnabled(false);
				}
				saveCanvasMenu.add(jSavePsMenuItem);				
			}
			fileMenu.add(saveCanvasMenu);
			
//			fileMenu.add(getPrintMenuItem());
			{
				garbageCollectMenuItem = new JMenuItem();
				garbageCollectMenuItem.setText("collect garbage");
				garbageCollectMenuItem.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						System.gc();
					}
				});				
			}
			fileMenu.add(garbageCollectMenuItem);
			{
				exitMenuItem = new JMenuItem();
				exitMenuItem.setText("Exit");
				exitMenuItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
					}
				});
			}
			fileMenu.add(exitMenuItem);			
		}
		add(fileMenu);
		{
			objectivesMenu = new JMenu("Objectives");
			main.objectives.addAsRadioButtonMenuItems(objectivesMenu);
		}
		add(objectivesMenu);
		{
			viewMenu = new JMenu();
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
			main.ss.soundOn.addAsCheckBoxMenuItem(viewMenu);
			Main.debug.addAsCheckBoxMenuItem(viewMenu);

		}
		add(viewMenu);
		{
			projMenu = new JMenu();
			projMenu.setText("Projection");
			projMenu.setBounds(0, 0, 19, 18);
			main.perspective.addAsRadioButtonMenuItems(projMenu);
			projMenu.addSeparator();
			main.ss.orientation3d.addAsRadioButtonMenuItems(projMenu);
			projMenu.addSeparator();
			main.ss.orientation4d.addAsRadioButtonMenuItems(projMenu);
		}
		add(projMenu);
		{
			screenMenu = new JMenu();
			screenMenu.setText("Screen");
			main.showedScreen.addAsRadioButtonMenuItems(screenMenu);			
		}
		add(screenMenu);
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
				    		ImageIO.write(main.viewScreen.buffImg,"png",chooser.getSelectedFile());
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
				    		ImageIO.write(main.viewScreen.buffImg,"jpg",chooser.getSelectedFile());
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
}
