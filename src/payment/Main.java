package payment;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main{
    public static void main(String[] args){
        JFrame frame = new JFrame("Payment System");
        frame.setSize(300,300);


        JPanel mainPanel = new JPanel(new GridLayout(3,1));

        JPanel panel = new JPanel(new GridLayout(3,2));

        JLabel unitL = new JLabel("Unit");
        JTextField unitF = new JTextField(10);

        JLabel idL = new JLabel("Registration ID");
        JTextField idF = new JTextField(10);

        JLabel takaL = new JLabel("Taka");
        JTextField takaF = new JTextField(10);

        JButton button = new JButton("Send Money");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String unit[] = new String[1];
                unit[0] = unitF.getText().toString().trim();
                String tempID = idF.getText().toString().trim();
                String tempTaka = takaF.getText().toString().trim();
                if(unit[0].equals("")|| tempID.equals("")||tempTaka.equals("")){
                    JOptionPane.showMessageDialog(null,"Input Error");
                }else {
                    Long taka = Long.parseLong(tempTaka);
                    Long id = Long.parseLong(tempID);
                    System.out.println(unit[0]+id+taka);
                    Boolean flag = PAYMENT.sendMoney(id,unit,taka);
                    if (flag){
                        JOptionPane.showMessageDialog(null,"Payment Successful");
                    }else {
                        JOptionPane.showMessageDialog(null ,"Something Wrong or Already paid");
                    }
                }

            }
        });

        panel.add(unitL);
        panel.add(unitF);
        panel.add(idL);
        panel.add(idF);
        panel.add(takaL);
        panel.add(takaF);

        mainPanel.add(panel);
        mainPanel.add(button);

        frame.add(mainPanel);

        frame.setLocation(300,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        String[] units = {"A","F"};
        Long regID = 2017000100l;
        Long taka = 12001l;


    }
}
