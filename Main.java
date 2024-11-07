import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SuppressWarnings("unused")
public class Main {
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static List<Cita> citas = new ArrayList<>();
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::createAndShowGUI);
    }
    
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Gestión de Citas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Panel de formulario
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        
        JLabel nombreLabel = new JLabel("Nombre del Cliente:");
        JTextField nombreField = new JTextField();
        
        JLabel fechaLabel = new JLabel("Fecha de la Cita (dd/MM/yyyy):");
        JTextField fechaField = new JTextField();
        
        JLabel horaLabel = new JLabel("Hora de la Cita (HH:mm):");
        JTextField horaField = new JTextField();
        
        JLabel servicioLabel = new JLabel("Servicio:");
        JTextField servicioField = new JTextField();
        
        formPanel.add(nombreLabel);
        formPanel.add(nombreField);
        formPanel.add(fechaLabel);
        formPanel.add(fechaField);
        formPanel.add(horaLabel);
        formPanel.add(horaField);
        formPanel.add(servicioLabel);
        formPanel.add(servicioField);

        // Botones de acción
        JButton agendarButton = new JButton("Agendar Cita");
        JButton modificarButton = new JButton("Modificar Cita");
        JButton cancelarButton = new JButton("Cancelar Cita");
        
        // Tabla para mostrar las citas
        String[] columnNames = {"Cliente", "Fecha", "Hora", "Servicio"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable citasTable = new JTable(tableModel);
        
        JScrollPane tableScrollPane = new JScrollPane(citasTable);
        
        // Área para mensajes de estado
        JTextArea mensajeArea = new JTextArea(3, 30);
        mensajeArea.setEditable(false);
        
        // Acción para agendar citas
        agendarButton.addActionListener(e -> {
            try {
                String nombreCliente = nombreField.getText();
                String fechaStr = fechaField.getText();
                Date fecha = sdf.parse(fechaStr);
                String hora = horaField.getText();
                String servicio = servicioField.getText();

                if (nombreCliente.isEmpty() || hora.isEmpty() || servicio.isEmpty()) {
                    mensajeArea.setText("Todos los campos son obligatorios.");
                    return;
                }
                
                Cliente cliente = new Cliente(nombreCliente);
                Cita cita = new Cita(fecha, hora, servicio, cliente);
                citas.add(cita);
                
                tableModel.addRow(new Object[]{nombreCliente, fechaStr, hora, servicio});
                mensajeArea.setText("Cita agendada para " + nombreCliente);
                
                // Limpiar campos
                nombreField.setText("");
                fechaField.setText("");
                horaField.setText("");
                servicioField.setText("");
            } catch (ParseException ex) {
                mensajeArea.setText("Error en el formato de fecha. Use dd/MM/yyyy.");
            }
        });

        // Acción para modificar citas
        modificarButton.addActionListener(e -> {
            int selectedRow = citasTable.getSelectedRow();
            if (selectedRow != -1) {
                try {
                    String nombreCliente = nombreField.getText();
                    String fechaStr = fechaField.getText();
                    Date fecha = sdf.parse(fechaStr);
                    String hora = horaField.getText();
                    String servicio = servicioField.getText();

                    if (nombreCliente.isEmpty() || hora.isEmpty() || servicio.isEmpty()) {
                        mensajeArea.setText("Todos los campos son obligatorios para modificar.");
                        return;
                    }
                    
                    Cita cita = citas.get(selectedRow);
                    cita.setCliente(new Cliente(nombreCliente));
                    cita.setFecha(fecha);
                    cita.setFecha(hora);
                    cita.setServicio(servicio);
                    
                    tableModel.setValueAt(nombreCliente, selectedRow, 0);
                    tableModel.setValueAt(fechaStr, selectedRow, 1);
                    tableModel.setValueAt(hora, selectedRow, 2);
                    tableModel.setValueAt(servicio, selectedRow, 3);
                    
                    mensajeArea.setText("Cita modificada para " + nombreCliente);
                    
                } catch (ParseException ex) {
                    mensajeArea.setText("Error en el formato de fecha.");
                }
            } else {
                mensajeArea.setText("Seleccione una cita en la tabla para modificarla.");
            }
        });
        
        // Acción para cancelar citas
        cancelarButton.addActionListener(e -> {
            int selectedRow = citasTable.getSelectedRow();
            if (selectedRow != -1) {
                citas.remove(selectedRow);
                tableModel.removeRow(selectedRow);
                mensajeArea.setText("Cita cancelada.");
            } else {
                mensajeArea.setText("Seleccione una cita en la tabla para cancelarla.");
            }
        });

        // Organizar componentes en el marco
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(agendarButton);
        buttonPanel.add(modificarButton);
        buttonPanel.add(cancelarButton);

        frame.getContentPane().add(formPanel, BorderLayout.NORTH);
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);
        frame.getContentPane().add(tableScrollPane, BorderLayout.SOUTH);
        frame.getContentPane().add(new JScrollPane(mensajeArea), BorderLayout.AFTER_LAST_LINE);
        
        frame.setVisible(true);
    }
}
