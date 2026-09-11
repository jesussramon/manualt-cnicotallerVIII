package py.edu.facitec.orionf.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class PantallaPrincipal extends JFrame {

    private static final long serialVersionUID = 1L;


    private final Color AZUL = new Color(0, 120, 255);
    private final Color AZUL_OSCURO = new Color(30, 45, 65);
    private final Color FONDO = new Color(245, 246, 248);
    private final Color BLANCO = Color.WHITE;
    private final Color TEXTO = new Color(45, 45, 45);
    private final Color GRIS = new Color(110, 110, 110);

    public PantallaPrincipal() {

        configurarVentana();

        construirInterfaz();
    }



    private void configurarVentana() {

        setTitle("ORIONF - Sistema Académico");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setSize(1200, 750);

        setMinimumSize(new Dimension(1000, 650));

        setLocationRelativeTo(null);
    }

    

    private void construirInterfaz() {

        JPanel panelPrincipal =
                new JPanel(new BorderLayout());

        panelPrincipal.setBackground(FONDO);

        // Menú lateral
        panelPrincipal.add(
                crearMenuLateral(),
                BorderLayout.WEST
        );

        // Contenido
        panelPrincipal.add(
                crearContenido(),
                BorderLayout.CENTER
        );

        setContentPane(panelPrincipal);
    }

  

    private JPanel crearMenuLateral() {

        JPanel menu =
                new JPanel(new BorderLayout());

        menu.setPreferredSize(
                new Dimension(230, 0)
        );

        menu.setBackground(AZUL_OSCURO);

     
        JPanel cabecera =
                new JPanel(
                        new GridLayout(2, 1)
                );

        cabecera.setBackground(
                AZUL_OSCURO
        );

        cabecera.setBorder(
                BorderFactory.createEmptyBorder(
                        25,
                        10,
                        25,
                        10
                )
        );

        JLabel lblLogo =
                new JLabel(
                        "ORIONF",
                        SwingConstants.CENTER
                );

        lblLogo.setForeground(BLANCO);

        lblLogo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel lblSistema =
                new JLabel(
                        "Sistema Académico",
                        SwingConstants.CENTER
                );

        lblSistema.setForeground(BLANCO);

        lblSistema.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        cabecera.add(lblLogo);
        cabecera.add(lblSistema);

        menu.add(
                cabecera,
                BorderLayout.NORTH
        );

        

        JPanel panelBotones =
                new JPanel(
                        new GridLayout(
                                0,
                                1,
                                0,
                                5
                        )
                );

        panelBotones.setBackground(
                AZUL_OSCURO
        );

        panelBotones.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        10,
                        10
                )
        );

        agregarBoton(
                panelBotones,
                "Dashboard"
        );

        agregarBoton(
                panelBotones,
                "Usuarios"
        );

        agregarBoton(
                panelBotones,
                "Facultades"
        );

        agregarBoton(
                panelBotones,
                "Proveedores"
        );

        agregarBoton(
                panelBotones,
                "Categorías"
        );

        agregarBoton(
                panelBotones,
                "Insumos"
        );

        agregarBoton(
                panelBotones,
                "Inventario"
        );

        agregarBoton(
                panelBotones,
                "Compras"
        );

        agregarBoton(
                panelBotones,
                "Solicitudes"
        );

        agregarBoton(
                panelBotones,
                "Entregas"
        );

        agregarBoton(
                panelBotones,
                "Reportes"
        );

        JScrollPane scroll =
                new JScrollPane(
                        panelBotones
                );

        scroll.setBorder(null);

        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_NEVER
        );

        menu.add(
                scroll,
                BorderLayout.CENTER
        );

       

        JButton btnSalir =
                crearBoton("Salir");

        btnSalir.addActionListener(
                e -> {

                    dispose();

                    new Login().setVisible(true);
                }
        );

        JPanel panelSalir =
                new JPanel(
                        new BorderLayout()
                );

        panelSalir.setBackground(
                AZUL_OSCURO
        );

        panelSalir.setBorder(
                BorderFactory.createEmptyBorder(
                        10,
                        10,
                        20,
                        10
                )
        );

        panelSalir.add(
                btnSalir,
                BorderLayout.CENTER
        );

        menu.add(
                panelSalir,
                BorderLayout.SOUTH
        );

        return menu;
    }

    

    private void agregarBoton(
            JPanel panel,
            String texto) {

        JButton boton =
                crearBoton(texto);

        panel.add(boton);
    }

    private JButton crearBoton(
            String texto) {

        JButton boton =
                new JButton(texto);

        boton.setForeground(BLANCO);

        boton.setBackground(AZUL);

        boton.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        boton.setHorizontalAlignment(
                SwingConstants.LEFT
        );

        boton.setFocusPainted(false);

        boton.setBorderPainted(false);

        boton.setMargin(
                new Insets(
                        8,
                        15,
                        8,
                        10
                )
        );

        return boton;
    }


    private JPanel crearContenido() {

        JPanel contenido =
                new JPanel(
                        new BorderLayout()
                );

        contenido.setBackground(FONDO);

        // Barra superior
        contenido.add(
                crearBarraSuperior(),
                BorderLayout.NORTH
        );

        // Dashboard
        contenido.add(
                crearDashboard(),
                BorderLayout.CENTER
        );

        return contenido;
    }

   

    private JPanel crearBarraSuperior() {

        JPanel barra =
                new JPanel(
                        new BorderLayout()
                );

        barra.setBackground(BLANCO);

        barra.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createMatteBorder(
                                0,
                                0,
                                1,
                                0,
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                25,
                                15,
                                25
                        )
                )
        );

        JLabel titulo =
                new JLabel(
                        "Dashboard General"
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        24
                )
        );

        titulo.setForeground(TEXTO);

        JLabel usuario =
                new JLabel(
                        "Usuario: Administrador"
                );

        usuario.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        usuario.setForeground(GRIS);

        barra.add(
                titulo,
                BorderLayout.WEST
        );

        barra.add(
                usuario,
                BorderLayout.EAST
        );

        return barra;
    }

    // ==============================
    // DASHBOARD
    // ==============================

    private JPanel crearDashboard() {

        JPanel dashboard =
                new JPanel(
                        new BorderLayout(
                                15,
                                15
                        )
                );

        dashboard.setBackground(FONDO);

        dashboard.setBorder(
                BorderFactory.createEmptyBorder(
                        20,
                        25,
                        25,
                        25
                )
        );

        // Tarjetas
        dashboard.add(
                crearTarjetas(),
                BorderLayout.NORTH
        );

        // Información
        dashboard.add(
                crearInformacion(),
                BorderLayout.CENTER
        );

        return dashboard;
    }

   

    private JPanel crearTarjetas() {

        JPanel tarjetas =
                new JPanel(
                        new GridLayout(
                                1,
                                4,
                                15,
                                0
                        )
                );

        tarjetas.setBackground(FONDO);

        tarjetas.add(
                crearTarjeta(
                        "Usuarios",
                        "0",
                        "Registrados"
                )
        );

        tarjetas.add(
                crearTarjeta(
                        "Insumos",
                        "0",
                        "Registrados"
                )
        );

        tarjetas.add(
                crearTarjeta(
                        "Compras",
                        "0",
                        "Realizadas"
                )
        );

        tarjetas.add(
                crearTarjeta(
                        "Solicitudes",
                        "0",
                        "Pendientes"
                )
        );

        return tarjetas;
    }

    private JPanel crearTarjeta(
            String titulo,
            String valor,
            String descripcion) {

        JPanel tarjeta =
                new JPanel(
                        new BorderLayout()
                );

        tarjeta.setBackground(BLANCO);

        tarjeta.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                15,
                                18,
                                15,
                                18
                        )
                )
        );

        JLabel lblTitulo =
                new JLabel(titulo);

        lblTitulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        lblTitulo.setForeground(TEXTO);

        JLabel lblValor =
                new JLabel(
                        valor
                );

        lblValor.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        30
                )
        );

        lblValor.setForeground(AZUL);

        JLabel lblDescripcion =
                new JLabel(
                        descripcion
                );

        lblDescripcion.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );

        lblDescripcion.setForeground(GRIS);

        tarjeta.add(
                lblTitulo,
                BorderLayout.NORTH
        );

        tarjeta.add(
                lblValor,
                BorderLayout.CENTER
        );

        tarjeta.add(
                lblDescripcion,
                BorderLayout.SOUTH
        );

        return tarjeta;
    }

    

    private JPanel crearInformacion() {

        JPanel informacion =
                new JPanel(
                        new GridLayout(
                                1,
                                2,
                                15,
                                0
                        )
                );

        informacion.setBackground(FONDO);

        informacion.add(
                crearSolicitudes()
        );

        informacion.add(
                crearAlertas()
        );

        return informacion;
    }

    

    private JPanel crearSolicitudes() {

        JPanel panel =
                crearPanelBlanco();

        JLabel titulo =
                new JLabel(
                        "Solicitudes recientes"
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        titulo.setForeground(TEXTO);

        JLabel mensaje =
                new JLabel(
                        "<html>"
                        + "No hay solicitudes registradas.<br><br>"
                        + "Las solicitudes realizadas aparecerán "
                        + "en esta sección."
                        + "</html>"
                );

        mensaje.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        mensaje.setForeground(GRIS);

        panel.add(
                titulo,
                BorderLayout.NORTH
        );

        panel.add(
                mensaje,
                BorderLayout.CENTER
        );

        return panel;
    }

    

    private JPanel crearAlertas() {

        JPanel panel =
                crearPanelBlanco();

        JLabel titulo =
                new JLabel(
                        "Alertas de stock"
                );

        titulo.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        18
                )
        );

        titulo.setForeground(TEXTO);

        JLabel mensaje =
                new JLabel(
                        "<html>"
                        + "No existen alertas actualmente.<br><br>"
                        + "Los insumos que alcancen el stock mínimo "
                        + "aparecerán aquí."
                        + "</html>"
                );

        mensaje.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        14
                )
        );

        mensaje.setForeground(GRIS);

        panel.add(
                titulo,
                BorderLayout.NORTH
        );

        panel.add(
                mensaje,
                BorderLayout.CENTER
        );

        return panel;
    }

    

    private JPanel crearPanelBlanco() {

        JPanel panel =
                new JPanel(
                        new BorderLayout(
                                10,
                                10
                        )
                );

        panel.setBackground(BLANCO);

        panel.setBorder(
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(
                                new Color(
                                        220,
                                        220,
                                        220
                                )
                        ),
                        BorderFactory.createEmptyBorder(
                                20,
                                20,
                                20,
                                20
                        )
                )
        );

        return panel;
    }

    
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(
                () -> {

                    PantallaPrincipal principal =
                            new PantallaPrincipal();

                    principal.setExtendedState(
                            JFrame.MAXIMIZED_BOTH
                    );

                    principal.setVisible(true);
                }
        );
    }
}