package src;
import java.util.HashMap;
import java.util.Map;

public class ImpostoPorEstado implements ImpostoStrategy {
    private static final Map<Estado, Double> ALIQUOTAS = new HashMap<>();

    static {
        ALIQUOTAS.put(Estado.SP, 0.20);
        ALIQUOTAS.put(Estado.RJ, 0.15);
        ALIQUOTAS.put(Estado.MG, 0.10);
        ALIQUOTAS.put(Estado.ES, 0.12);
        ALIQUOTAS.put(Estado.RS, 0.14);
        ALIQUOTAS.put(Estado.SC, 0.14);
        ALIQUOTAS.put(Estado.PR, 0.14);
        ALIQUOTAS.put(Estado.BA, 0.13);
        ALIQUOTAS.put(Estado.PE, 0.13);
        ALIQUOTAS.put(Estado.CE, 0.13);
        ALIQUOTAS.put(Estado.DF, 0.12);
        ALIQUOTAS.put(Estado.PB, 0.01);
        ALIQUOTAS.put(Estado.GO, 0.16);
        ALIQUOTAS.put(Estado.AM, 0.10);


    }
    private Estado estado;

    public ImpostoPorEstado(Estado estado) { this.estado = estado; }
    @Override
    public double calcularImposto(double valor) {
        double aliquota = ALIQUOTAS.getOrDefault(estado, 0.08);
        return valor * aliquota;
    }
}

