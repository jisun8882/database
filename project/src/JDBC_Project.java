import java.awt.Color;
import java.awt.Font;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.MenuItem;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

public class JDBC_Project implements ActionListener  {
	  
   private JFileChooser ch = new JFileChooser();
   
   private MenuBar mb = new MenuBar();
   private Menu m1 = new Menu("Menu");	      
   private MenuItem f1 = new MenuItem("Open");
   private MenuItem f2 = new MenuItem("Login");
   
   private JFrame frame = new JFrame();
   private JLabel titleLabel = new JLabel(); //제목 Label
   private JPanel table_panel = new JPanel();
   private JPanel buy_panel = new JPanel();
   private JPanel menu_panel = new JPanel();
   private JPanel assign_panel = new JPanel();
   
   public static JTextArea check_area = new JTextArea();
   private JTextField con_nameinput = new JTextField();
   private JLabel con_name = new JLabel("고객명");
   private JLabel tab_name = new JLabel("테이블명");
   private JComboBox<Integer> tab_num = new JComboBox<Integer>();
   private JButton orderButton = new JButton("주문");
   private JButton cancelButton = new JButton("취소");
   private JButton payButton = new JButton("결제");
   private JLabel[] r = new JLabel[20];
   
   private JButton[] menuButton = new JButton[20];
   
   private JPanel assign_panel_1 = new JPanel();
   private JPanel assign_panel_2 = new JPanel();
   private JPanel assign_panel_3 = new JPanel();
   private JPanel assign_panel_4 = new JPanel();
      
   private JTextField con_nameinput_b = new JTextField();
   private JLabel con_name_b = new JLabel("고객명");
   private JButton joinButton = new JButton("가입");
   private JButton concheckButton = new JButton("조회");
   public static JTextArea check_area_b = new JTextArea();
   
   private JTextField staff_input = new JTextField();
   private JLabel staff = new JLabel("직원명");
   private JButton staffassignButton = new JButton("직원등록");
   private JButton staffcheckButton = new JButton("조회");
   public static JTextArea check_area_d = new JTextArea();
   
   private JLabel term = new JLabel("기간");
   private JComboBox <String> dt;
   public static JTextArea check_area_c;
   private JScrollPane scroll;
   
   private JTextField menu_input = new JTextField();
   private JLabel menu = new JLabel("메뉴명");
   private JButton menuassignButton = new JButton("메뉴등록");
   private JButton menucheckButton = new JButton("조회");
   public static JTextArea check_area_e = new JTextArea();
   
   public JDBC_Project() throws SQLException {
	      frame.setTitle("식당 관리 시스템");
	      frame.setSize(620,740);
	      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	      frame.setLayout(null);
	      frame.setResizable(false);
	   
	      
	      f1.addActionListener(this);
	      f2.addActionListener(this);
	      
	      m1.add(f1);
	      m1.add(f2);
	      mb.add(m1);
	      	      
	      frame.setMenuBar(mb);
	      
	      titleLabel.setText("식당 주문관리");
	      titleLabel.setFont(new Font("필기체",1,35));
	      titleLabel.setBorder(new LineBorder(Color.gray, 2));
	      titleLabel.setHorizontalAlignment(JLabel.CENTER);
	      titleLabel.setBounds(5, 5, 600, 100);
	      
	      table_panel.setFont(new Font("필기체", 1, 12));
	      table_panel.setBorder(new TitledBorder("테이블 현황"));
	      table_panel.setBounds(5, 110, 300, 250);
	      table_panel.setLayout(null);
	      
	      
	      int j = 1;
	      int x = 10, y = 20;
	      int w = 48, h = 48;
	      for(int i =0;i<20;i++){
	         String r_j =  String.valueOf(j);
	         r[i] = new JLabel(r_j,JLabel.CENTER);
	         r[i].setBackground(Color.WHITE);
	         r[i].setBorder(new LineBorder(Color.BLACK));
	         r[i].setOpaque(true);
	         r[i].setBounds(x,y,w,h);
	         table_panel.add(r[i]);
	         
	         j += 1;
	         
	         if(x == 242){
	            y += 58;
	            x = 10;
	         }
	         else
	            x += 58;
	      }
	      
	      
	      buy_panel.setFont(new Font("필기체", 1, 12));
	      buy_panel.setBorder(new TitledBorder("주문내역"));
	      buy_panel.setBounds(310, 110, 300, 250);
	      buy_panel.setLayout(null);
	      
	      
	      int numbers_to_add_max = 20;
			for (int i = 1; i <= numbers_to_add_max; i++) {
				tab_num.addItem(new Integer(i));
			}
			
		  tab_num.addActionListener(this);
	      
	      check_area.setBounds(10,20,180,220);
	      con_name.setBounds(200, 10, 60, 30);
	      con_nameinput.setBounds(200, 40, 80, 30);
	      tab_name.setBounds(200,70,70,30);
	      tab_num.setBounds(220,100,60,25);
	      orderButton.setBounds(230, 135, 60, 30);
	      cancelButton.setBounds(230, 170, 60, 30);
	      payButton.setBounds(230, 205, 60, 30);
	      
	      orderButton.addActionListener(this);
	      cancelButton.addActionListener(this);
	      payButton.addActionListener(this);
	      
	      buy_panel.add(check_area);
	      buy_panel.add(con_nameinput);
	      buy_panel.add(con_name);
	      buy_panel.add(tab_name);
	      buy_panel.add(tab_num);
	      buy_panel.add(orderButton);
	      buy_panel.add(cancelButton);
	      buy_panel.add(payButton);
	      
	      check_area.setEditable(false);
	      
	      menu_panel.setFont(new Font("필기체", 1, 12));
	      menu_panel.setBorder(new TitledBorder("메뉴"));
	      menu_panel.setBounds(5, 365, 300, 320);
	      menu_panel.setLayout(null);
	     	      
	      int x_b = 10, y_b = 20;
	      int w_b = 135, h_b = 24;
	    	 
	      for(int i =0;i<20;i++){
	    	  
	    	 JDBC_Project_Sql.get_menu();
	    	  
	    	 menuButton[i] = new JButton(JDBC_Project_Sql.menu[i]);
	         menuButton[i].setBounds(x_b,y_b,w_b,h_b);
	         menu_panel.add(menuButton[i]);
	         
	         if(x_b == 155){
	            y_b += 29;
	            x_b = 10;
	         }
	         else
	            x_b += 145;
	         
	         menuButton[i].addActionListener(this);
	      }

	      assign_panel.setFont(new Font("필기체", 1, 12));
	      assign_panel.setBorder(new TitledBorder("등록/조회"));
	      assign_panel.setBounds(310, 365, 300, 320);
	      assign_panel.setLayout(null);	      
	      
	      assign_panel_1.setLayout(null);
	      assign_panel_2.setLayout(null);
	      assign_panel_3.setLayout(null);
	      assign_panel_4.setLayout(null);
	      
	      JTabbedPane tab = new JTabbedPane(JTabbedPane.TOP);
	      tab.add("고객",assign_panel_1);
	      tab.add("매출",assign_panel_2);
	      tab.add("직원",assign_panel_3);
	      tab.add("메뉴",assign_panel_4);
	      tab.setBounds(10,20,280,290);
	      
	      assign_panel.add(tab);
	      
	      con_nameinput_b.setBounds(20, 40, 80, 30);
	      con_name_b.setBounds(20, 10, 60, 30);
	      joinButton.setBounds(130, 40, 60, 30);
	      concheckButton.setBounds(200, 40, 60, 30);
	      check_area_b.setBounds(10, 90, 255, 160);
	      check_area_b.setBorder(new LineBorder(Color.black, 1));
	      check_area_b.setEditable(false);
	      
	      joinButton.addActionListener(this);
	      concheckButton.addActionListener(this);
	      
	      assign_panel_1.add(con_nameinput_b);
	      assign_panel_1.add(con_name_b);
	      assign_panel_1.add(joinButton);
	      assign_panel_1.add(concheckButton);
	      assign_panel_1.add(check_area_b);
	      
	      
	      JLabel term = new JLabel("기간");
	      dt= new JComboBox<String> ();
	      check_area_c = new JTextArea();

	      scroll = new JScrollPane(check_area_c);
	      scroll.setViewportView(check_area_c);
	      term.setBounds(20, 30, 60, 30);
	      dt.setBounds(70, 30, 120, 30);
	      check_area_c.setBounds(10, 90, 255, 300);
	      scroll.setBounds(10, 90, 255, 160);
	      check_area_c.setBorder(new LineBorder(Color.black, 1));
	      check_area_c.setEditable(false);
	      
	      SimpleDateFormat dateF = new SimpleDateFormat("yyyy-MM-dd");
	      
	      GregorianCalendar calendar = new GregorianCalendar();
	      calendar.add( GregorianCalendar.DAY_OF_MONTH, -30 );
	      
	      for( int i = 0; i <30 ; i++ )
	      {
	    	  calendar.add( GregorianCalendar.DAY_OF_MONTH, 1 );
	    	  dt.addItem(dateF.format(calendar.getTime()));
	      
	      }

    	  dt.addActionListener(this);
	      
	      assign_panel_2.add(term);
	      assign_panel_2.add(dt);
	      assign_panel_2.add(scroll);
	      
	      staff_input.setBounds(20, 40, 80, 30);
	      staff.setBounds(20, 10, 60, 30);
	      staffassignButton.setBounds(110, 40, 90, 30);
	      staffcheckButton.setBounds(205, 40, 60, 30);
	      check_area_d.setBounds(10, 90, 255, 160);
	      check_area_d.setBorder(new LineBorder(Color.black, 1));
	      check_area_d.setEditable(false);

	      assign_panel_3.add(staff_input);
	      assign_panel_3.add(staff);
	      assign_panel_3.add(staffassignButton);
	      assign_panel_3.add(staffcheckButton);
	      assign_panel_3.add(check_area_d);
	      
	      staffassignButton.addActionListener(this);
	      staffcheckButton.addActionListener(this);
	      
	      check_area.setBorder(new LineBorder(Color.black, 1));
	      check_area.setEditable(false);
	      
	      menu_input.setBounds(20, 40, 80, 30);
	      menu.setBounds(20, 10, 60, 30);
	      menuassignButton.setBounds(110, 40, 90, 30);
	      menucheckButton.setBounds(205, 40, 60, 30);
	      check_area_e.setBounds(10, 90, 255, 160);
	      
	      check_area_e.setBorder(new LineBorder(Color.black, 1));
	      check_area_e.setEditable(false);
	      
	      assign_panel_4.add(menu_input);
	      assign_panel_4.add(menu);
	      assign_panel_4.add(menuassignButton);
	      assign_panel_4.add(menucheckButton);
	      assign_panel_4.add(check_area_e);
	      
	      menuassignButton.addActionListener(this);
	      menucheckButton.addActionListener(this);
	      
	      frame.add(titleLabel);
	      frame.add(table_panel);
	      frame.add(buy_panel);
	      frame.add(menu_panel);
	      frame.add(assign_panel);


	      frame.setVisible(true);
	     
	   }
  
   
   public void re_button() throws SQLException{
	   JButton[] menuButton = new JButton[20];
	      int x_b = 10, y_b = 20;
	      int w_b = 135, h_b = 24;
	      menu_panel.removeAll();
	      for(int i =0;i<20;i++){
	    	 JDBC_Project_Sql.get_menu();
	    	 menuButton[i] = new JButton(JDBC_Project_Sql.menu[i]);
	         menuButton[i].setBounds(x_b,y_b,w_b,h_b);
	         menu_panel.add(menuButton[i]);
	         
	         if(x_b == 155){
	            y_b += 29;
	            x_b = 10;
	         }
	         else
	            x_b += 145;
	         
	         menuButton[i].addActionListener(this);
	      }
	      
   }
   public void yellow_t(int t_n) throws SQLException{
	   t_n = t_n -1;
	   r[t_n].setBackground(Color.YELLOW);
       r[t_n].setBorder(new LineBorder(Color.BLACK));
       r[t_n].setOpaque(true);
       table_panel.validate();
       table_panel.repaint();
   }
   public void white_t(int t_n) throws SQLException{
	   t_n = t_n -1;
	   r[t_n].setBackground(Color.WHITE);
       r[t_n].setBorder(new LineBorder(Color.BLACK));
       r[t_n].setOpaque(true);
       table_panel.validate();
       table_panel.repaint();
   }
   public void actionPerformed(ActionEvent e) {
	   String new_menu;
	   int new_menu_price;
	      if (e.getSource() == joinButton) {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"권한이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
	    		  join_consume();
	    	  }
	      }
	      else if(e.getSource().equals(dt))
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"권한이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
		    	  String dt_combo = (String) dt.getSelectedItem();
		    	  try {
					JDBC_Project_Sql.check_pay(dt_combo);
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
	    	  }

	      }
	      else if (e.getSource() == concheckButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"로그인이 필요합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
			    	  String conname = con_nameinput_b.getText();
			    	  try {
						JDBC_Project_Sql.check_consumer(conname);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"등록된 고객이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
					}
	    	  }
	      }
	      else if (e.getSource() == menuassignButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"권한이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
	    		  assign_menu();
	    	  }
	      }
	      else if (e.getSource() == menucheckButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"로그인이 필요합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
		    	  String menuname = menu_input.getText();
		    	  try {
					JDBC_Project_Sql.check_menu(menuname);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"등록된 메뉴가 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
	    	  }
	      }
	      else if (e.getSource() == staffassignButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"권한이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
	    		  assign_staff();
	    	  }
	      }
	      else if (e.getSource() == staffcheckButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"로그인이 필요합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
		    	  String staffname = staff_input.getText();
		    	  try {
					JDBC_Project_Sql.check_staff(staffname);
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"등록된 직원이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
	    	  }
	      }
	      else if (e.getSource() == f1)
	      {
	    	  int ret = ch.showOpenDialog(null);
	          if(ret != JFileChooser.APPROVE_OPTION){
	             JOptionPane.showMessageDialog(null, "경로를 선택하지 않았습니다.","경고",JOptionPane.WARNING_MESSAGE);
	             return;
	          }
	          String filePath = ch.getSelectedFile().getPath();
	          
	         try {
	             JDBC_Project_Sql.insertPath(filePath);
	          } catch (IOException e1) {
	             e1.printStackTrace();
	          } catch (SQLException e1) {
	             e1.printStackTrace();
	          }
	        try {
				re_button();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	        menu_panel.revalidate();
	        menu_panel.repaint();
	      }
	      else if (e.getSource() == f2)
	      {
	    	  login();
	      }
	      else if (e.getSource() == join_cancelButton)
	      {
	    	  frame_join.dispose();
	      }
	      else if (e.getSource() == menu_cancelButton)
	      {
	    	  frame_assignmenu.dispose();
	      }
	      else if (e.getSource() == staff_cancelButton)
	      {
	    	  frame_assignstaff.dispose();
	      }
	      else if (e.getSource() == menu_assignButton)
	      {
	    	  new_menu = menunameinput.getText();
	    	  new_menu_price = Integer.parseInt(menupriceinput.getText());

	    	  try {
				JDBC_Project_Sql.input_menu(new_menu,new_menu_price);
				frame_assignmenu.dispose();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		       try {
					re_button();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
		        menu_panel.revalidate();
		        menu_panel.repaint();

	      }
	      else if (e.getSource() == join_joinButton)
	      {
	    	  String conname = connameinput.getText();
	    	  int conbirth = Integer.parseInt(conbrithinput.getText());
	    	  int conphone = Integer.parseInt(conphoneinput.getText());
	    	  try {
				JDBC_Project_Sql.input_consumer(conname,conbirth,conphone);
				frame_join.dispose();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	      }
	      else if (e.getSource() == staff_assignButton)
	      {
	    	  String staffname = staffnameinput.getText();
	    	  String stlevel = (String) stafflevel.getSelectedItem();
	    	  try {
				JDBC_Project_Sql.input_staff(staffname,stlevel);
				frame_assignstaff.dispose();

			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	      }
	      else if(e.getSource() == orderButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"로그인이 필요합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
		    	  if((check_area.getText()).equals(""))
		    	  {
		    		  JOptionPane.showMessageDialog(null,"주문 내역이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		    	  }
		    	  else{
			    	  int t_num = (Integer) tab_num.getSelectedItem();
			    	  String c_name = con_nameinput.getText();
			    	  String a = JDBC_Project_Sql.specification_m;
			    	  String[] a_n = a.split("\n");
			    	  for(int i = 0; i < a_n.length; i++){
			    		  String[] a_t = a_n[i].split("\t");
			    		  int a_t_int = Integer.parseInt(a_t[1]);
			    		  try {
							JDBC_Project_Sql.insertOrder(t_num,c_name, a_t[0],a_t_int);
							yellow_t(t_num);
							check_area.setText("");
							JDBC_Project_Sql.total = 0;
						} catch (SQLException e1) {
						}
			    	  }
		    	  }
	    	  }
	      }
	      else if (e.getSource() == cancelButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"로그인이 필요합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
		    	  if((check_area.getText()).equals(""))
		    	  {
		    		  JOptionPane.showMessageDialog(null,"취소 내역이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
		    	  }
		    	  else
		    	  {
			    	  int t_num = (Integer) tab_num.getSelectedItem();
			    	  try {
						JDBC_Project_Sql.del_order(t_num);
						white_t(t_num);
						check_area.setText("");
					} catch (SQLException e1) {
					}
		    	  }
	    	  }
	      }
	      else if (e.getSource() == tab_num)
	      {
	    	  int t_num = (Integer) tab_num.getSelectedItem();
	    	  JDBC_Project_Sql.specification_m = "";
	    	  try {
				JDBC_Project_Sql.check_table(t_num);
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	      }
	      else if(e.getSource() == payButton)
	      {
	    	  if(JDBC_Project_Sql.level_chk != 1){
	    		  JOptionPane.showMessageDialog(null,"로그인이 필요합니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
	    	  if(check_area.getText().equals(""))
	    	  {
	    		  JOptionPane.showMessageDialog(null,"결재 내역이 없습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  }
	    	  else
	    	  {
		    	  int t_num = (Integer) tab_num.getSelectedItem();
		    	  String c_name = con_nameinput.getText();
		    	  String s_name = JDBC_Project_Sql.current_staff;
		    	  int tot_price = 0;
	    	  
				try {
					tot_price = JDBC_Project_Sql.get_total(t_num);
				} catch (SQLException e2) {
	
				}
			
			 try {
				if(c_name.equals("")){
					JDBC_Project_Sql.insertpay(t_num, c_name, s_name, tot_price);
					white_t(t_num);
					check_area.setText("");
					try {
						JDBC_Project_Sql.Updateconsumer(tot_price, c_name);
						JDBC_Project_Sql.Updatestaff(tot_price, s_name);
					} catch (IOException e1) {

					}
					JOptionPane.showMessageDialog(null,"결제를 완료하셨습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
				}
				
				else{
						if(JDBC_Project_Sql.check_c(c_name)){
								
								String level = JDBC_Project_Sql.check_level(c_name);
								System.out.println (level);
								
								if(level.equals("Gold")){
								tot_price = (int) (tot_price * 0.7);
								JDBC_Project_Sql.insertpay(t_num, c_name, s_name, tot_price);
								white_t(t_num);
								check_area.setText("");
								try {
									JDBC_Project_Sql.Updateconsumer(tot_price, c_name);
									JDBC_Project_Sql.Updatestaff(tot_price, s_name);
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								JOptionPane.showMessageDialog(null,"결제를 완료하셨습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
								// update 직원 ..?
								}
								else if (level.equals("Silver"))
								{
									tot_price = (int) (tot_price * 0.8);
									JDBC_Project_Sql.insertpay(t_num, c_name, s_name, tot_price);
									white_t(t_num);
									check_area.setText("");
									try {
										JDBC_Project_Sql.Updateconsumer(tot_price, c_name);
										JDBC_Project_Sql.Updatestaff(tot_price, s_name);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null,"결제를 완료하셨습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
								}
								else if (level.equals("Bronze"))
								{
									tot_price = (int) (tot_price * 0.9);
									JDBC_Project_Sql.insertpay(t_num, c_name, s_name, tot_price);
									white_t(t_num);
									check_area.setText("");
									try {
										JDBC_Project_Sql.Updateconsumer(tot_price, c_name);
										JDBC_Project_Sql.Updatestaff(tot_price, s_name);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null,"결제를 완료하셨습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
								}
								else
								{
									JDBC_Project_Sql.insertpay(t_num, c_name, s_name, tot_price);
									white_t(t_num);
									check_area.setText("");
									try {
										JDBC_Project_Sql.Updateconsumer(tot_price, c_name);
										JDBC_Project_Sql.Updatestaff(tot_price, s_name);
									} catch (IOException e1) {
										e1.printStackTrace();
									}
									JOptionPane.showMessageDialog(null,"결제를 완료하셨습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
								}
								
								
							}
							else{
								JOptionPane.showMessageDialog(null,"등록되지 않은 고객입니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
							}
				}
						} catch (SQLException e1) {
						}
		    	  }
	    	  }
	      }
	      
	      else if (e.getSource() == loginButton)
	      {
	    	  String staffname = idInput.getText();
	    	  int staffid = Integer.parseInt(pwdInput.getText());
	    	  try {
				JDBC_Project_Sql.login_staff(staffname, staffid);
			    frame_login.dispose();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
	      }
	      else if(e.getSource() instanceof JButton){
	    	  String text = ((JButton)e.getSource()).getText();
	    	  JOptionPane.showMessageDialog(null,text+"를 주문하셨습니다.", "알림", JOptionPane.INFORMATION_MESSAGE);
	    	  try {
				JDBC_Project_Sql.order_menu(text);
				
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			}  
	      }
	      

	   }
   
   private JFrame frame_login;
   private JPanel loginpanel;
   private JLabel idLabel ;
   private JLabel pwdLabel ;
   private JTextField idInput;
   private JPasswordField pwdInput;
   private JButton loginButton;
   
   public void login() {
	   frame_login = new JFrame();
	   loginpanel = new JPanel();
	   idLabel = new JLabel("이름");
	   pwdLabel = new JLabel("사원번호");
	   idInput = new JTextField();
	   pwdInput = new JPasswordField();
	   loginButton = new JButton("로그인"); 
	   
      loginpanel.setLayout(null);
      idLabel.setBounds(20, 10, 60, 30);
      pwdLabel.setBounds(20, 50, 60, 30);
      idInput.setBounds(100, 10, 80, 30);
      pwdInput.setBounds(100, 50, 80, 30);
      loginButton.setBounds(200, 25, 80, 35);
      
      loginpanel.add(idLabel);
      loginpanel.add(pwdLabel);
      loginpanel.add(idInput);
      loginpanel.add(pwdInput);
      loginpanel.add(loginButton);
      
      frame_login.add(loginpanel);

      frame_login.setTitle("사원 로그인");
      frame_login.setSize(320,130);
      frame_login.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      frame_login.setVisible(true);
      
      loginButton.addActionListener(this);
      
   }
   
   private JFrame frame_join;
   private JPanel joinpanel;
   private JLabel connameLabel;
   private JLabel conbrithLabel;
   private JLabel conphoneLabel;
   private JTextField connameinput;
   private JTextField conbrithinput;
   private JTextField conphoneinput;
   private JButton join_joinButton;
   private JButton join_cancelButton;
   
   public void join_consume() {
	   
	   frame_join = new JFrame();
	   joinpanel = new JPanel();
	   connameLabel = new JLabel("고객명");
	   conbrithLabel = new JLabel("생일(4자리)");
	   conphoneLabel = new JLabel("연락처");
	   connameinput = new JTextField();
	   conbrithinput = new JTextField();
	   conphoneinput = new JTextField();
	   join_joinButton = new JButton("가입신청");
	   join_cancelButton = new JButton("취소");
      
	   joinpanel.setLayout(null);
       connameLabel.setBounds(20, 10, 70, 30);
       conbrithLabel.setBounds(20, 60, 70, 30);
       conphoneLabel.setBounds(20, 110, 70, 30);
       connameinput.setBounds(110, 10, 100, 30);
       conbrithinput.setBounds(110, 60, 100, 30);
       conphoneinput.setBounds(110, 110, 100, 30);
       join_joinButton.setBounds(10, 160, 100, 35);
       join_cancelButton.setBounds(120, 160, 100, 35);
       
       joinpanel.add(connameLabel);
       joinpanel.add(conbrithLabel);
       joinpanel.add(conphoneLabel);
       joinpanel.add(connameinput);
       joinpanel.add(conbrithinput);
       joinpanel.add(conphoneinput);
       joinpanel.add(join_joinButton);
       joinpanel.add(join_cancelButton);
       
       
       frame_join.add(joinpanel);

       frame_join.setTitle("회원등록");
       frame_join.setSize(250,250);
       frame_join.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame_join.setVisible(true);
       
       join_joinButton.addActionListener(this);
       join_cancelButton.addActionListener(this);
   }
   
   private JFrame frame_assignmenu;
   private JPanel menupanel;
   private JLabel menunameLabel;
   private JLabel menupriceLabel;
   private JTextField menunameinput;
   private JTextField menupriceinput;
   private JButton menu_assignButton;
   private JButton menu_cancelButton;
   
   
   public void assign_menu() {
	   
	   frame_assignmenu = new JFrame();
	   menupanel = new JPanel();
	   menunameLabel = new JLabel("메뉴명");
	   menupriceLabel = new JLabel("가격");
	   menunameinput = new JTextField();
	   menupriceinput = new JTextField();
	   menu_assignButton = new JButton("등록");
	   menu_cancelButton = new JButton("취소");

	   menupanel.setLayout(null);
       menunameLabel.setBounds(20, 10, 70, 30);
       menupriceLabel.setBounds(20, 60, 70, 30);
       menunameinput.setBounds(110, 10, 100, 30);
       menupriceinput.setBounds(110, 60, 100, 30);
       menu_assignButton.setBounds(10, 110, 100, 35);
       menu_cancelButton.setBounds(120, 110, 100, 35);
       
       menupanel.add(menunameLabel);
       menupanel.add(menupriceLabel);
       menupanel.add(menunameinput);
       menupanel.add(menupriceinput);
       menupanel.add(menu_assignButton);
       menupanel.add(menu_cancelButton);
       
       frame_assignmenu.add(menupanel);

       frame_assignmenu.setTitle("메뉴등록");
       frame_assignmenu.setSize(250,200);
       frame_assignmenu.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame_assignmenu.setVisible(true);
       
       menu_assignButton.addActionListener(this);
       menu_cancelButton.addActionListener(this);
   }
  
   private JFrame frame_assignstaff;
   private JPanel staffpanel;
   private JLabel staffnameLabel;
   private JLabel stafflevelLabel;
   private JTextField staffnameinput;
   private JComboBox<String> stafflevel;
   private JButton staff_assignButton;
   private JButton staff_cancelButton;
   
   public void assign_staff() {
	   
	   frame_assignstaff = new JFrame();
	   staffpanel = new JPanel();
	   staffnameLabel = new JLabel("직원명");
	   stafflevelLabel = new JLabel("직급");
	   staffnameinput = new JTextField();
	   stafflevel = new JComboBox<String>();
	   staff_assignButton = new JButton("등록");
	   staff_cancelButton = new JButton("취소");
	      
	   staffpanel.setLayout(null);
       staffnameLabel.setBounds(20, 10, 70, 30);
       stafflevelLabel.setBounds(20, 60, 70, 30);
       staffnameinput.setBounds(110, 10, 100, 30);
       stafflevel.setBounds(110, 60, 100, 30);
       staff_assignButton.setBounds(10, 110, 100, 35);
       staff_cancelButton.setBounds(120, 110, 100, 35);
       
       stafflevel.addItem("Supervisor");
       stafflevel.addItem("Staff");
       
       staffpanel.add(staffnameLabel);
       staffpanel.add(stafflevelLabel);
       staffpanel.add(staffnameinput);
       staffpanel.add(stafflevel);
       staffpanel.add(staff_assignButton);
       staffpanel.add(staff_cancelButton);
       
       frame_assignstaff.add(staffpanel);

       frame_assignstaff.setTitle("직원등록");
       frame_assignstaff.setSize(250,200);
       frame_assignstaff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
       frame_assignstaff.setVisible(true);
       
       staff_assignButton.addActionListener(this);
       staff_cancelButton.addActionListener(this);
   }
   
   public static void main(String[] args) throws SQLException {
	   JDBC_Project_Sql.connectDB();
	   new JDBC_Project();
   }
}

