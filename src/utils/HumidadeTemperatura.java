package utils;

public class HumidadeTemperatura {
	private String dataMedicao;
	private String horaMedicao;
	private double valorMedicaoTemperatura;
	private double valorMedicaoHumidade;
	public String getDataMedicao() {
		return dataMedicao;
	}
	public void setDataMedicao(String dataMedicao) {
		this.dataMedicao = dataMedicao;
	}
	public String getHoraMedicao() {
		return horaMedicao;
	}
	public void setHoraMedicao(String horaMedicao) {
		this.horaMedicao = horaMedicao;
	}
	public double getValorMedicaoTemperatura() {
		return valorMedicaoTemperatura;
	}
	public void setValorMedicaoTemperatura(double valorMedicaoTemperatura) {
		this.valorMedicaoTemperatura = valorMedicaoTemperatura;
	}
	public double getValorMedicaoHumidade() {
		return valorMedicaoHumidade;
	}
	public void setValorMedicaoHumidade(double valorMedicaoHumidade) {
		this.valorMedicaoHumidade = valorMedicaoHumidade;
	}
	public HumidadeTemperatura(String dataMedicao, String horaMedicao, double valorMedicaoTemperatura,
			double valorMedicaoHumidade) {
		super();
		this.dataMedicao = dataMedicao;
		this.horaMedicao = horaMedicao;
		this.valorMedicaoTemperatura = valorMedicaoTemperatura;
		this.valorMedicaoHumidade = valorMedicaoHumidade;
	}
	@Override
	public String toString() {
		return "HumidadeTemperatura [dataMedicao=" + dataMedicao + ", horaMedicao=" + horaMedicao
				+ ", valorMedicaoTemperatura=" + valorMedicaoTemperatura + ", valorMedicaoHumidade="
				+ valorMedicaoHumidade + "]";
	}
	
}
