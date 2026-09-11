package py.edu.facitec.orionf.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Login extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTextField txtUsuario;
    private JPasswordField txtPassword;

    // Colores de la interfaz
    private final Color COLOR_FONDO = new Color(238, 241, 245);
    private final Color COLOR_AZUL = new Color(0, 120, 255);
    private final Color COLOR_TEXTO = new Color(45, 45, 45);
    private final Color COLOR_GRIS = new Color(120, 120, 120);

    public Login() {

        configurarVentana();

        construirInterfaz();
    }

    // =========================================================
    // CONFIGURACIÓN DE LA VENTANA
    // =========================================================

    private void configurarVentana() {

        setTitle("ORIONF - Sistema Académico");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(900, 750);

        setLocationRelativeTo(null);

        setResizable(false);
    }

    // =========================================================
    // CONSTRUCCIÓN DE LA INTERFAZ
    // =========================================================

    private void construirInterfaz() {

        JPanel panelPrincipal = new JPanel(new BorderLayout());

        panelPrincipal.setBackground(COLOR_FONDO);

        construirEncabezado(panelPrincipal);

        construirFormulario(panelPrincipal);

        setContentPane(panelPrincipal);
    }

    // =========================================================
    // ENCABEZADO
    // =========================================================

    private void construirEncabezado(JPanel panelPrincipal) {

        JPanel encabezado = new JPanel();

        encabezado.setLayout(new GridBagLayout());

        encabezado.setBackground(COLOR_FONDO);

        GridBagConstraints gbc = new GridBagConstraints();

        gbc.gridx = 0;
        gbc.gridy = 0;

        gbc.insets = new Insets(20, 0, 5, 0);

        // -----------------------------------------------------
        // LOGO
        // -----------------------------------------------------

        JLabel lblLogo = new JLabel();

        try {

            ImageIcon iconoOriginal = new ImageIcon(
                    getClass().getResource(
                            "/py/edu/facitec/orionf/img/logo_orionf.png"
                    )
            );

            java.awt.Image imagen = iconoOriginal.getImage();

            java.awt.Image imagenRedimensionada =
                    imagen.getScaledInstance(
                            220,
                            220,
                            java.awt.Image.SCALE_SMOOTH
                    );

            ImageIcon icono =
                    new ImageIcon(imagenRedimensionada);

            lblLogo.setIcon(icono);

        } catch (Exception e) {

            // Si no encuentra el logo,
            // mostramos el texto como alternativa.

            lblLogo.setText("ORIONF");

            lblLogo.setFont(
                    new Font("Arial", Font.BOLD, 52)
            );

            lblLogo.setForeground(Color.BLACK);
        }

        lblLogo.setHorizontalAlignment(
                SwingConstants.CENTER
        );

        encabezado.add(lblLogo, gbc);

        // -----------------------------------------------------
        // NOMBRE DEL SISTEMA
        // -----------------------------------------------------

        gbc.gridy = 1;

        gbc.insets = new Insets(0, 0, 5, 0);

        JLabel lblSistema =
                new JLabel("Sistema Academico");

        lblSistema.setFont(
                new Font("Arial", Font.BOLD, 28)
        );

        lblSistema.setForeground(COLOR_TEXTO);

        encabezado.add(lblSistema, gbc);

        // -----------------------------------------------------
        // FILIAL
        // -----------------------------------------------------

        gbc.gridy = 2;

        gbc.insets = new Insets(0, 0, 15, 0);

        JLabel lblFilial =
                new JLabel("Filial Katuete");

        lblFilial.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        lblFilial.setForeground(COLOR_TEXTO);

        encabezado.add(lblFilial, gbc);

        panelPrincipal.add(
                encabezado,
                BorderLayout.NORTH
        );
    }

    // =========================================================
    // FORMULARIO DE LOGIN
    // =========================================================

    private void construirFormulario(JPanel panelPrincipal) {

        JPanel contenedor =
                new JPanel(new GridBagLayout());

        contenedor.setBackground(COLOR_FONDO);

        // -----------------------------------------------------
        // TARJETA BLANCA
        // -----------------------------------------------------

        JPanel tarjeta =
                new JPanel(new GridBagLayout());

        tarjeta.setPreferredSize(
                new Dimension(450, 430)
        );

        tarjeta.setBackground(Color.WHITE);

        tarjeta.setBorder(
                BorderFactory.createLineBorder(
                        new Color(220, 220, 220)
                )
        );

        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.gridx = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        gbc.weightx = 1;

        // -----------------------------------------------------
        // TÍTULO
        // -----------------------------------------------------

        gbc.gridy = 0;

        gbc.insets =
                new Insets(25, 25, 15, 25);

        JLabel lblTitulo =
                new JLabel(
                        "Iniciar Sesión",
                        SwingConstants.CENTER
                );

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        20
                )
        );

        lblTitulo.setForeground(COLOR_GRIS);

        tarjeta.add(lblTitulo, gbc);

        // -----------------------------------------------------
        // CAMPO USUARIO
        // -----------------------------------------------------

        gbc.gridy = 1;

        gbc.insets =
                new Insets(10, 25, 5, 25);

        txtUsuario =
                new JTextField();

        txtUsuario.setPreferredSize(
                new Dimension(400, 45)
        );

        txtUsuario.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        txtUsuario.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        190,
                                        200,
                                        215
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                )
        );

        txtUsuario.setToolTipText(
                "Ingrese su usuario"
        );

        tarjeta.add(txtUsuario, gbc);

        // -----------------------------------------------------
        // CAMPO PASSWORD
        // -----------------------------------------------------

        gbc.gridy = 2;

        gbc.insets =
                new Insets(10, 25, 10, 25);

        txtPassword =
                new JPasswordField();

        txtPassword.setPreferredSize(
                new Dimension(400, 45)
        );

        txtPassword.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        txtPassword.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        190,
                                        200,
                                        215
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                5,
                                12,
                                5,
                                12
                        )
                )
        );

        txtPassword.setToolTipText(
                "Ingrese su contraseña"
        );

        tarjeta.add(txtPassword, gbc);

        // -----------------------------------------------------
        // BOTÓN INICIAR
        // -----------------------------------------------------

        gbc.gridy = 3;

        gbc.insets =
                new Insets(10, 25, 15, 25);

        JButton btnIniciar =
                new JButton("Iniciar");

        btnIniciar.setPreferredSize(
                new Dimension(400, 48)
        );

        btnIniciar.setBackground(
                COLOR_AZUL
        );

        btnIniciar.setForeground(
                Color.WHITE
        );

        btnIniciar.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        18
                )
        );

        btnIniciar.setFocusPainted(false);

        btnIniciar.setBorderPainted(false);

        btnIniciar.addActionListener(
                e -> iniciarSesion()
        );

        tarjeta.add(btnIniciar, gbc);

        // -----------------------------------------------------
        // SEPARADOR
        // -----------------------------------------------------

        gbc.gridy = 4;

        gbc.insets =
                new Insets(0, 25, 15, 25);

        JLabel lblSeparador =
                new JLabel(
                        "- O -",
                        SwingConstants.CENTER
                );

        lblSeparador.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        lblSeparador.setForeground(
                COLOR_GRIS
        );

        tarjeta.add(lblSeparador, gbc);

        // -----------------------------------------------------
        // MENSAJE DE SOPORTE
        // -----------------------------------------------------

        gbc.gridy = 5;

        gbc.insets =
                new Insets(5, 25, 5, 25);

        JLabel lblSoporte =
                new JLabel(
                        "<html><center>"
                        + "Si encuentra algún inconveniente, favor<br>"
                        + "contactar con el soporte"
                        + "</center></html>",
                        SwingConstants.CENTER
                );

        lblSoporte.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        16
                )
        );

        lblSoporte.setForeground(
                new Color(
                        0,
                        100,
                        255
                )
        );

        tarjeta.add(lblSoporte, gbc);

        contenedor.add(tarjeta);

        panelPrincipal.add(
                contenedor,
                BorderLayout.CENTER
        );
    }

    // =========================================================
    // INICIAR SESIÓN
    // =========================================================

    private void iniciarSesion() {

        String usuario =
                txtUsuario.getText().trim();

        String password =
                new String(
                        txtPassword.getPassword()
                );

        // -----------------------------------------------------
        // VALIDAR CAMPOS
        // -----------------------------------------------------

        if (usuario.isEmpty()
                || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Debe ingresar usuario y contraseña.",
                    "Aviso",
                    JOptionPane.WARNING_MESSAGE
            );

            return;
        }

        // -----------------------------------------------------
        // POR AHORA:
        // SI LOS CAMPOS TIENEN DATOS,
        // ABRIMOS LA PANTALLA PRINCIPAL.
        // -----------------------------------------------------

        PantallaPrincipal principal =
                new PantallaPrincipal();

        principal.setVisible(true);

        dispose();
    }

    // =========================================================
    // MAIN
    // =========================================================

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                () -> {

                    Login login =
                            new Login();

                    login.setVisible(true);
                }
        );
    }
}