
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Desplazamiento_Giro extends javax.swing.JFrame {

    int distancia=0, angulo=0;
    int Xtraslacion=0, Ytraslacion=0;   //Coordenada final al trasladar la imagen
    int Xgiro=0, Ygiro=0;   //Coordenada final al girar la imagen
    int OrigX, OrigY; //Coordenadas iniciales de la imagen (centro del panel)
    int Xsum=0, Ysum=0; //distancias para corregir rotacion
    Image imgF;
    private AffineTransform trans = new AffineTransform();
    
    public Desplazamiento_Giro(){
        initComponents();
        setLocationRelativeTo(null);
                
        OrigX = panel.getWidth()/2;
        OrigY = panel.getHeight()/2;
        
        txtDistancia.setEnabled(false);
        txtPointX.setEnabled(false);
        txtPointY.setEnabled(false);
        txtAngulo.setEnabled(false);
        rbtTraslacion.setEnabled(false);
        rbtGiro.setEnabled(false);
        btnMover.setEnabled(false);
        sAngulo.setEnabled(false);
        sDistancia.setEnabled(false);
    }

    public void limpiar(){
        txtDistancia.setText("");
        txtAngulo.setText("");
        txtPointX.setText("");
        txtPointY.setText("");
    }
    
    public void movimiento(){
        
        double ang=0; //angulo en radianes
        
        if (rbtTraslacion.isSelected()) {
            distancia = Integer.parseInt(txtDistancia.getText());
            ang = Math.toRadians(angulo); 
            
            traslacion(ang, OrigX, OrigY);
        }else if(rbtGiro.isSelected()){    
            Xgiro = Integer.parseInt(txtPointX.getText());
            Ygiro = Integer.parseInt(txtPointY.getText());
            //Calculando distancia por pitagoras
            distancia = (int) Math.sqrt(Math.pow(Xgiro-OrigX,2) + Math.pow(Ygiro - OrigY, 2)) ;

            double y, x, tangente;
            if (OrigX > Xgiro) {     //II ó III cuadrante
                y =(Ygiro - OrigY);
                x = (OrigX - Xgiro);
                tangente = y/x;
                ang = Math.toRadians( Math.toDegrees(Math.atan(tangente)) + angulo );
            }else{                  //I ó IV cuadrante
                y =(Ygiro - OrigY);
                x = (Xgiro - OrigX);
                tangente = y/x;
                ang = Math.toRadians( (180 - Math.toDegrees(Math.atan(tangente))) + angulo );
            }
            
            traslacion(ang, Xgiro, Ygiro);
        }
    }
    
    public void traslacion(double ang, int Xinicial, int Yinicial){
        Graphics g = panel.getGraphics();
        g.clearRect(0, 0, panel.getWidth(), panel.getHeight());//Limpiando graficos anteriores
        g.drawImage(imgF, OrigX, OrigY, this);
        Graphics2D gr = (Graphics2D) g;
        gr.setColor(Color.red);
        
        Xtraslacion = (int)(Xinicial + distancia*Math.cos(ang));
        Ytraslacion = (int)(Yinicial - distancia*Math.sin(ang));
        
        //Validando Coordenada de Traslacion
        if (Xtraslacion <= 0 || Xtraslacion >= panel.getWidth() ||
            Ytraslacion <= 0 || Ytraslacion >= panel.getHeight() ||
            Xinicial <= 0 || Xinicial >= panel.getWidth() ||
            Yinicial <= 0 || Yinicial >= panel.getHeight()) {
            JOptionPane.showMessageDialog(null, "Coordenada fuera de rango", "Error", JOptionPane.WARNING_MESSAGE);
        }else{
            //Dibujando imagen despues del traslado
            if (rbtTraslacion.isSelected()) {

                gr.drawImage(imgF, Xtraslacion, Ytraslacion, this);   //Dibujando imagen trasladada

            }else if(rbtGiro.isSelected()){

                gr.drawLine(OrigX, OrigY, Xinicial, Yinicial);
                gr.fillOval(Xinicial-5, Yinicial-5, 10, 10);

                giro(); //Dibujando imagen rotada
            }
            gr.drawLine(Xinicial, Yinicial, Xtraslacion, Ytraslacion);  // Linea del punto original, al punto seleccionado
        }
    }
    
    public void giro(){
        Graphics g = panel.getGraphics();
        Graphics2D gr = (Graphics2D) g;
        
        correcionGiro(); //Corrigiendo vertice de la imagen antes de rotar imagen
        
        trans.setToIdentity();
        trans.translate(Xtraslacion + Xsum, Ytraslacion - Ysum);    //Sumamos las correcciones de la rotacion
        trans.rotate(Math.toRadians(angulo*(-1)), imgF.getWidth(this) / 2,imgF.getHeight(this) / 2);
        gr.drawImage(imgF, trans, this);    // Imagen en el punto final despues del giro
    }
    
    //La imagen a cargar debe ser (50 x 50) px
    public void correcionGiro(){
        double ang, distanciaTemp;
        distanciaTemp = Math.sqrt(2)*25;    //distancia es igual a la mitad de la diagonal de la imagen
        ang = 135 + angulo; //sumamos 135 porque hasta la diagonal de la imagen hay 135 grados
        Xsum = OrigX - (int)(OrigX + 25 + distanciaTemp*Math.cos(Math.toRadians(ang))); //distancia en X para corregir
        Ysum = (int)(OrigY + 25 - distanciaTemp*Math.sin(Math.toRadians(ang))) - OrigY; //distancia en Y para corregir
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        panel = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        btnMover = new javax.swing.JButton();
        txtPointX = new javax.swing.JTextField();
        txtPointY = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        txtAngulo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtDistancia = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        rbtTraslacion = new javax.swing.JRadioButton();
        rbtGiro = new javax.swing.JRadioButton();
        btnCargarImagen = new javax.swing.JButton();
        sAngulo = new javax.swing.JSlider();
        sDistancia = new javax.swing.JSlider();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout panelLayout = new javax.swing.GroupLayout(panel);
        panel.setLayout(panelLayout);
        panelLayout.setHorizontalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1021, Short.MAX_VALUE)
        );
        panelLayout.setVerticalGroup(
            panelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 651, Short.MAX_VALUE)
        );

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        btnMover.setText("MOVER");
        btnMover.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMoverActionPerformed(evt);
            }
        });

        txtPointX.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        txtPointY.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("PUNTO INICIAL");

        txtAngulo.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("ANGULO");

        txtDistancia.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txtDistancia.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("DISTANCIA");

        rbtTraslacion.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbtTraslacion);
        rbtTraslacion.setText("Traslacion");
        rbtTraslacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtTraslacionActionPerformed(evt);
            }
        });

        rbtGiro.setBackground(new java.awt.Color(255, 255, 255));
        buttonGroup1.add(rbtGiro);
        rbtGiro.setText("Giro");
        rbtGiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbtGiroActionPerformed(evt);
            }
        });

        btnCargarImagen.setText("Cargar Imagen");
        btnCargarImagen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarImagenActionPerformed(evt);
            }
        });

        sAngulo.setMaximum(360);
        sAngulo.setOrientation(javax.swing.JSlider.VERTICAL);
        sAngulo.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sAnguloStateChanged(evt);
            }
        });

        sDistancia.setMaximum(600);
        sDistancia.setOrientation(javax.swing.JSlider.VERTICAL);
        sDistancia.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                sDistanciaStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnCargarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(rbtTraslacion)
                        .addGap(10, 10, 10)
                        .addComponent(rbtGiro))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(txtPointX, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addComponent(txtPointY, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(btnMover, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(sAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(sDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addComponent(btnCargarImagen, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rbtTraslacion)
                    .addComponent(rbtGiro))
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(14, 14, 14)
                .addComponent(txtDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(14, 14, 14)
                .addComponent(txtAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addGap(14, 14, 14)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtPointX, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtPointY, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(btnMover, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(sAngulo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sDistancia, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(panel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCargarImagenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarImagenActionPerformed
        JFileChooser ventana = new JFileChooser();
        FileNameExtensionFilter filtro1 = new FileNameExtensionFilter("*jpg","jpg");
        FileNameExtensionFilter filtro2 = new FileNameExtensionFilter("*png","png");
        ventana.setFileFilter(filtro2);
        ventana.setFileFilter(filtro1);
        ventana.setMultiSelectionEnabled(false);
        ventana.setAcceptAllFileFilterUsed(false);
        int opcion = ventana.showOpenDialog(this);
        if(opcion == JFileChooser.APPROVE_OPTION){
            ImageIcon icon = new ImageIcon(ventana.getSelectedFile().toString());
            imgF = icon.getImage();
        }
        Graphics g = panel.getGraphics();
        g.drawImage(imgF, OrigX, OrigY, this);
        
        rbtTraslacion.setEnabled(true);
        rbtGiro.setEnabled(true);
        sAngulo.setEnabled(true);
    }//GEN-LAST:event_btnCargarImagenActionPerformed

    private void btnMoverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMoverActionPerformed
        angulo = Integer.parseInt(txtAngulo.getText());

        movimiento();
    }//GEN-LAST:event_btnMoverActionPerformed

    private void rbtTraslacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtTraslacionActionPerformed
        txtDistancia.setEnabled(true);
        txtPointX.setEnabled(false);
        txtPointY.setEnabled(false);
        txtAngulo.setEnabled(true);
        btnMover.setEnabled(true);
        limpiar();
        sDistancia.setEnabled(true);
    }//GEN-LAST:event_rbtTraslacionActionPerformed

    private void rbtGiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbtGiroActionPerformed
        txtDistancia.setEnabled(false);
        txtPointX.setEnabled(true);
        txtPointY.setEnabled(true);
        txtAngulo.setEnabled(true);
        btnMover.setEnabled(true);
        limpiar();
        sDistancia.setEnabled(false);
    }//GEN-LAST:event_rbtGiroActionPerformed

    private void sAnguloStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sAnguloStateChanged
        txtAngulo.setText(String.valueOf(sAngulo.getValue()));
        angulo = sAngulo.getValue();

        movimiento();
    }//GEN-LAST:event_sAnguloStateChanged

    private void sDistanciaStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_sDistanciaStateChanged
        txtAngulo.setText(String.valueOf(sAngulo.getValue()));
        txtDistancia.setText(String.valueOf(sDistancia.getValue()));
        angulo = sAngulo.getValue();
        double ang; //angulo en radianes
        distancia = sDistancia.getValue();
        ang = Math.toRadians(angulo); 
        
        traslacion(ang, OrigX, OrigY);
    }//GEN-LAST:event_sDistanciaStateChanged

    
    
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Desplazamiento_Giro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Desplazamiento_Giro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Desplazamiento_Giro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Desplazamiento_Giro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Desplazamiento_Giro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCargarImagen;
    private javax.swing.JButton btnMover;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel panel;
    private javax.swing.JRadioButton rbtGiro;
    private javax.swing.JRadioButton rbtTraslacion;
    private javax.swing.JSlider sAngulo;
    private javax.swing.JSlider sDistancia;
    private javax.swing.JTextField txtAngulo;
    private javax.swing.JTextField txtDistancia;
    private javax.swing.JTextField txtPointX;
    private javax.swing.JTextField txtPointY;
    // End of variables declaration//GEN-END:variables

}
