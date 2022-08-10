
package reto5;

import java.util.ArrayList;
import java.sql.*;
import java.util.Arrays;
import javax.swing.JOptionPane;

public class crudProducto {
    
    public static ArrayList<Producto> productos = new ArrayList<>();
    public static Reto5 conexion = new Reto5();
    public static Connection acceso = conexion.crearConexion();
    
    public static ArrayList<Producto> consultarProducto() {
        Producto producto;
        productos.clear();
        try {
            Statement sentencia = acceso.createStatement();
            ResultSet datos = sentencia.executeQuery("select * from producto");
            while (datos.next()) {
                producto = new Producto(Integer.parseInt(datos.getString(1)), datos.getString(2), Integer.parseInt(datos.getString(3)), datos.getString(4), datos.getString(5));
                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return productos;
    }
    
    public static void crearProducto(Producto producto) {
        productos.add(producto);

        try {
            String sql_insert = "INSERT INTO producto (pro_id, pro_nombre, pro_precio, pro_descripcion, pro_fecha_vencimiento) VALUES (?,?,?,?,?)";
            PreparedStatement statement = acceso.prepareStatement(sql_insert);
            statement.setString(1, Integer.toString(producto.id));
            statement.setString(2, producto.nombre);
            statement.setString(3, Integer.toString(producto.precio));
            statement.setString(4, producto.descripcion);
            statement.setString(5, producto.fecha);

            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                JOptionPane.showMessageDialog(null,"Registro exitoso", "Registrar",JOptionPane.INFORMATION_MESSAGE);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al registrar", "Registrar",JOptionPane.ERROR_MESSAGE);
        }
    }
        
        public static void eliminarProducto(int id) {
        try {
            String sql_delete = "DELETE FROM producto WHERE pro_id=?";
            PreparedStatement statement = acceso.prepareStatement(sql_delete);
            statement.setInt(1, id);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro eliminado", "Eliminar",JOptionPane.INFORMATION_MESSAGE);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al eliminar", "Eliminar",JOptionPane.ERROR_MESSAGE);

        }
    }
        
        public static void modificarProducto(Producto producto) {
        try {
            String sql_update = "UPDATE producto SET pro_nombre=?, pro_precio=?, pro_descripcion=?, pro_fecha_vencimiento=?  WHERE pro_id=?";
            PreparedStatement statement = acceso.prepareStatement(sql_update);
            
            statement.setInt(5, producto.id);
            statement.setString(1, producto.nombre);
            statement.setInt(2, producto.precio);
            statement.setString(3, producto.descripcion);
            statement.setString(4, producto.fecha);
            statement.executeUpdate();
            JOptionPane.showMessageDialog(null,"Registro modificado", "Modificar",JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            JOptionPane.showMessageDialog(null,"Error al modificar", "Modificar",JOptionPane.ERROR_MESSAGE);

        }
    }
        
}
    
    

