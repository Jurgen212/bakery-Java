import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Container;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.GridLayout;

import javax.imageio.stream.MemoryCacheImageOutputStream;
import javax.swing.JButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.channels.SelectableChannel;
import java.util.ArrayList;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class App extends JFrame{


    //Se declararan los productos disponibles y se guardaran en un arreglo
    //Declaracion de bebidas
    bebida gaseosa      = new bebida("Gaseosa"  , 2000, tipo_bebida.fria        );
    bebida jugo         = new bebida("Jugo"     , 1000, tipo_bebida.fria        );
    bebida cafe         = new bebida("Cafe"     , 1500, tipo_bebida.caliente    );
    bebida chocolate    = new bebida("Chocolate", 3000, tipo_bebida.caliente    );
    bebida vive100      = new bebida("Vive 100" , 2000, tipo_bebida.energizante );
    bebida gatorade     = new bebida("Gatorade" , 1500, tipo_bebida.energizante );


    //Declaracion de alimentos

    alimento mani       = new alimento("Mani"       , 500   , tipo_alimento.salado  );
    alimento carne      = new alimento("Carne"      , 5000  , tipo_alimento.salado  );
    alimento papitas    = new alimento("Papitas"    , 1000  , tipo_alimento.salado  );
    alimento bananita   = new alimento("Bananita"   , 200   , tipo_alimento.dulce   );
    alimento chocoramo  = new alimento("Chocorramo" , 4000  , tipo_alimento.dulce   );
    alimento paleta     = new alimento("Paleta"     , 2000  , tipo_alimento.dulce   );

    producto lista_productos[] = { gaseosa, jugo, cafe, chocolate, vive100, gatorade, mani, carne, papitas, bananita, chocoramo, paleta };

    ArrayList<producto> lista_seleccionados = new ArrayList<producto>();
    ArrayList<Integer> lista_precios = new ArrayList<Integer>();
    //Creacion de elementos que iran en la interface
    Container contenedor;
    JPanel c_1_1, c_1_2, c_1_1_1, c_1_1_2, c_1_2_1, c_1_2_2, c_1_2_3;
    JComboBox listaOpciones;
    JTextArea mostrar_precio, mostrar_compra_actual_lista, compra_total;
    JButton boton_facturar, boton_agregar;
    JTextField input_cantidad;
    int n_elementos = 0;
    int total;

    String lista_elementos = "";
    manejador manejador = new manejador();

    //Creacion de constructor, donde se ejecutara nuestra interface

    public App(){
        super("Panaderia don chepe");

        contenedor = getContentPane();
        c_1_1   = new JPanel();
        c_1_2   = new JPanel();
        c_1_1_1 = new JPanel();
        c_1_1_2 = new JPanel();
        c_1_2_1 = new JPanel();
        c_1_2_2 = new JPanel();
        c_1_2_3 = new JPanel();




        String[] array_nombres = new String[ lista_productos.length ];

        for( int i = 0; i < lista_productos.length; i++){
            array_nombres[i] = lista_productos[i].getNombre();
        }
        //Caracteristicas del principal contenedor 

        //Titulo bienvenida
        contenedor.setLayout( new GridLayout( 1, 2));
        contenedor.add( c_1_1 );
        contenedor.add( c_1_2 );

        //Caracteristicas c_1_1 
        c_1_1.setLayout( new GridLayout( 2, 1 ));
        c_1_1.add( c_1_1_1 );
        c_1_1.add( c_1_1_2 );


        c_1_1_1.setLayout( new GridLayout( 3, 1));
        c_1_1_2.setLayout( new GridLayout( 4, 1));

        //Caracteristicas c_1_2
        c_1_2.setLayout( new GridLayout( 3, 1));
        c_1_2.add( c_1_2_1 );
        c_1_2.add( c_1_2_2 );
        c_1_2.add( c_1_2_3 );

        c_1_2_3.setLayout( new GridLayout( 1, 2));


        //Creacion de objetos que apareceran en la interface

        JTextArea titulo_bienvenida = new JTextArea( 5, 5 );
        titulo_bienvenida.setEditable( false );
        titulo_bienvenida.setText("Bienvenidos a la panaderia de Don Chepe");
        


        JTextArea titulo_opciones = new JTextArea( 5, 5);
        titulo_opciones.setEditable( false );
        titulo_opciones.setText("Seleccione producto: ");

        listaOpciones = new JComboBox<String>( array_nombres );
        listaOpciones.addItemListener( manejador );

        mostrar_precio = new JTextArea(10 ,5);
        mostrar_precio.setEditable( false );
        mostrar_precio.setText( "Aqui se mostrara el precio");

        //Creacion de opciones en N elementos

        JTextArea titulo_seleccion_n_productos = new JTextArea( 5, 5 );
        titulo_seleccion_n_productos .setEditable( false );
        titulo_seleccion_n_productos .setText("¿Cuantas unidades desea llevar? ");
        

        //Creacion de botones

        boton_facturar  = new JButton("Facturar");
        boton_agregar   = new JButton("Agregar" );
        boton_facturar.addActionListener( manejador );
        boton_agregar.addActionListener( manejador ) ;

        //Creacion de input
        input_cantidad = new JTextField();
        

        //Creacion de JTextArea que guarda la info de la compra actua


        mostrar_compra_actual_lista = new JTextArea(20, 40);
        mostrar_compra_actual_lista.setText(" ");
        mostrar_compra_actual_lista.setEditable( false );


        //JText area que guarda el total

        compra_total = new JTextArea(20, 40);
        compra_total.setText(" ");
        compra_total.setEditable( false );


        //Añadiendo al c_1_1_1
        c_1_1_1.add( titulo_bienvenida );
        c_1_1_1.add( titulo_opciones );
        c_1_1_1.add( listaOpciones );  
       

        //Añadiendo al c_1_1_2
        c_1_1_2.add( titulo_seleccion_n_productos );
        c_1_1_2.add( input_cantidad );
        c_1_1_2.add( mostrar_precio );


        //Añadiendo al c_1_2_1
        c_1_2_1.add( mostrar_compra_actual_lista );

        //Añadiendo al c_1_2_2
        c_1_2_2.add( compra_total );

        //Añadiendo al c_1_2_3
        c_1_2_3.add( boton_agregar  );
        c_1_2_3.add( boton_facturar );


        //Caracteristicas del contenedor
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setSize( 1000, 500);
        setVisible( true );

        
    }
    public static void main(String[] args) throws Exception {
        App app = new App();
    }


    public class manejador implements ActionListener, ItemListener{

         

        @Override
        public void actionPerformed(ActionEvent e) {
                if( e.getActionCommand() == "Agregar" ){
                    //El texto de total se elimina cada vez que se agregue un elemento
                    compra_total.setText("");

                    //n_elementos es el numero de veces que guardaremos un elemento en el arreglo de la compra ( CUANTAS VECES SE COMPRA UN PRODUCTO )
                    n_elementos = Integer.parseInt( input_cantidad.getText() );

                    //Este bucle recorre los productos y busca el que sea igual al seleccionado
                     for( int i = 0; i < lista_productos.length; i++){
     
                         if( listaOpciones.getSelectedItem() == lista_productos[i].getNombre() ){
                             
                             //Este try junto con el if, valoran si hay menos de 5 productos en la actual factura y no dejaran agregar mas
                             try{
                                 if( lista_seleccionados.size() <= 5 && ( lista_seleccionados.size() + n_elementos) <= 5 ){
                                    
                                    //Agregara el producto a la compra el numero de veces que tenga n_elementos que es la variable que guarda el numero ingresado por el usuario
                                     for( int k = 0; k < n_elementos; k++){
                                        lista_seleccionados.add( lista_productos[i] );
                                        lista_precios.add( lista_productos[i].getPrecio() );
                                     }

                                     for( int k = 0; k < n_elementos; k++){
                                        
                                        lista_elementos += lista_productos[i].getNombre() + " // ";
                                        mostrar_compra_actual_lista.setText( lista_elementos );

                                     }     
                                     
                                     n_elementos = 0; 
                                 }
                                 //Enviaremos la excepcion de que el limite de la compra se ha superado
                                 else{
                                     throw new Exception( "El limite de articulos para facturar es 5");
                                 }
     
                             }
                             //Mostrar la excepcion de que hemos excedido el limite de productos en lista
                             catch( Exception ex){
     
                                 JOptionPane.showMessageDialog( contenedor, ex.getMessage(), "Error", 1);
     
                             }
                         }
                     }
                     
                     //Haremos que el input tenga un texto de 0 y el n_elementos volvera a 0
                     input_cantidad.setText("0");
                       
                 }
                //Accion que se ejecuta al facturar
                
                if( e.getActionCommand() == "Facturar"){
                    compra_total.setText("");
                    try{

                        if( lista_seleccionados.size() == 0){
                            throw new Exception("Por favor ingrese minimo un producto para facturar :)");
                        }

                        else{
                            mostrar_compra_actual_lista.setText( "" );
                            for( int i = 0; i < lista_seleccionados.size(); i++){
                                total += lista_seleccionados.get( i ).getPrecio();
    
                            }
                            
                            lista_seleccionados.removeAll( lista_seleccionados );
                            compra_total.setText( "Total compra:" + total + " ");
                            total = 0;
                            
                            lista_elementos = "";
                        }
                    } 
                    catch( Exception ex){
                        JOptionPane.showMessageDialog( contenedor, ex.getMessage(), "Error", 1);
                    }
                    

                }
                                 
        }

        @Override
        public void itemStateChanged(ItemEvent e) {
            for( int i = 0; i < lista_productos.length; i++){

                if( listaOpciones.getSelectedItem() == lista_productos[i].getNombre() ){
                    mostrar_precio.setText( "El precio de " + listaOpciones.getSelectedItem() + " es " + lista_productos[i].getPrecio() );
                }
            }
            
        }

    }
}
