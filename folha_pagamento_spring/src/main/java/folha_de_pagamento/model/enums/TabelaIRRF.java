public enum TabelaIRRF {
    FAIXA_1(0.00, 1903.98, 0.0, 0.0),
    FAIXA_2(1903.99, 2826.65, 7.5, 142.80),
    FAIXA_3(2826.66, 3751.05, 15.0, 354.80),
    FAIXA_4(3751.06, 4664.68, 22.5, 636.13),
    FAIXA_5(4664.69, 4664.70, 27.5, 869.36);

    private final double faixaInicial;
    private final double faixaFinal;
    private final double aliquota;
    private final double deducao;  
