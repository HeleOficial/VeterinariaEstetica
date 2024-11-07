import java.util.Date;

public class Cita {
    private Date fecha;
    private String hora;
    private Cliente cliente;

    public Cita(Date fecha, String hora, String servicio, Cliente cliente) {
        this.fecha = fecha;
        this.hora = hora;
        this.cliente = cliente;
    }

    public void agendarCita() {
        // Lógica para verificar disponibilidad y agendar la cita
        System.out.println("Cita agendada para " + cliente.getNombre() + " en la fecha " + fecha + " a las " + hora);
    }

    public void cancelarCita() {
        // Lógica para cancelar la cita
        System.out.println("Cita cancelada para " + cliente.getNombre());
    }

    public void modificarCita(Date nuevaFecha, String nuevaHora) {
        this.fecha = nuevaFecha;
        this.hora = nuevaHora;
        System.out.println("Cita modificada para " + cliente.getNombre() + " a la nueva fecha " + nuevaFecha + " y hora " + nuevaHora);
    }

    public void setCliente(Cliente cliente2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setCliente'");
    }

    public void setFecha(String hora2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFecha'");
    }

    public void setServicio(String servicio) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setServicio'");
    }

    public void setFecha(Date fecha2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setFecha'");
    }
}
