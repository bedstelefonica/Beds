package beds.mbeans.KPIs;

import javax.annotation.PostConstruct;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

import beds.connectionbd.BaseDatos;

@ManagedBean(name = "KPImportes")
public class KPImportes implements Serializable {

	private static final long serialVersionUID = 1L;

	private BarChartModel barModel;

	int valor;
	int valor2;
	int valor3;
	int valor4;
	int ValorTotal1 = 0;
	int ValorTotal2 = 0;
	int ValorTotal3 = 0;
	int ValorTotal4 = 0;

	@PostConstruct
	public void init() throws SQLException {
		createBarModels();
	}

	public BarChartModel GastosTotales() {

		BarChartModel model = new BarChartModel();

		Connection con = BaseDatos.conexion();
		ResultSet miResulset = null;
		PreparedStatement ps = null;
		String consulta = "SELECT * FROM factura WHERE proveedor = 'Empresa1'";

		try {

			ps = con.prepareStatement(consulta);
			miResulset = ps.executeQuery();
			while (miResulset.next()) {

				valor = miResulset.getInt(5);
				ValorTotal1 = valor + ValorTotal1;
				valor = 0;
			}

			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String consulta2 = "SELECT * FROM factura WHERE proveedor = 'Empresa2'";

		try {

			ps = con.prepareStatement(consulta2);
			miResulset = ps.executeQuery();
			while (miResulset.next()) {

				valor2 = miResulset.getInt(5);
				ValorTotal2 = valor2 + ValorTotal2;
				valor2 = 0;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String consulta3 = "SELECT * FROM factura WHERE proveedor = 'Empresa3'";

		try {

			ps = con.prepareStatement(consulta3);
			miResulset = ps.executeQuery();
			while (miResulset.next()) {

				valor3 = miResulset.getInt(5);
				ValorTotal3 = valor3 + ValorTotal3;
				valor3 = 0;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String consulta4 = "SELECT * FROM factura WHERE proveedor = 'Empresa4'";

		try {

			ps = con.prepareStatement(consulta4);
			miResulset = ps.executeQuery();
			while (miResulset.next()) {

				valor4 = miResulset.getInt(5);
				ValorTotal4 = valor4 + ValorTotal4;
				valor4 = 0;
			}
			ps.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		ChartSeries Empresa1 = new ChartSeries();
		Empresa1.setLabel("Empresa1");
		ChartSeries Empresa2 = new ChartSeries();
		Empresa2.setLabel("Empresa2");
		ChartSeries Empresa3 = new ChartSeries();
		Empresa3.setLabel("Empresa3");
		ChartSeries Empresa4 = new ChartSeries();
		Empresa4.setLabel("Empresa4");
	
		Empresa1.set("ImporteTotal", ValorTotal1);
		Empresa2.set("ImporteTotal", ValorTotal2);
		Empresa3.set("ImporteTotal", ValorTotal3);
		Empresa4.set("ImporteTotal", ValorTotal4);
		model.addSeries(Empresa1);
		model.addSeries(Empresa2);
		model.addSeries(Empresa3);
		model.addSeries(Empresa4);

		return model;
	}

	public void createBarModels() throws SQLException {
		createBarModel();
	}

	public void createBarModel() throws SQLException {
		barModel = GastosTotales();

		barModel.setTitle("Bar Chart");
		barModel.setLegendPosition("ne");

		Axis xAxis = barModel.getAxis(AxisType.X);
		xAxis.setLabel("Grafica del total de los importes de la Empresas");

		Axis yAxis = barModel.getAxis(AxisType.Y);
		yAxis.setLabel("Cantidad");
		yAxis.setMin(0);
		yAxis.setMax(2000);
	}

	public BarChartModel getBarModel() {
		return barModel;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public int getValor2() {
		return valor2;
	}

	public void setValor2(int valor2) {
		this.valor2 = valor2;
	}

	public int getValor3() {
		return valor3;
	}

	public void setValor3(int valor3) {
		this.valor3 = valor3;
	}

	public int getValor4() {
		return valor4;
	}

	public void setValor4(int valor4) {
		this.valor4 = valor4;
	}

	public int getValorTotal1() {
		return ValorTotal1;
	}

	public void setValorTotal1(int valorTotal1) {
		ValorTotal1 = valorTotal1;
	}

	public int getValorTotal2() {
		return ValorTotal2;
	}

	public void setValorTotal2(int valorTotal2) {
		ValorTotal2 = valorTotal2;
	}

	public int getValorTotal4() {
		return ValorTotal4;
	}

	public void setValorTotal4(int valorTotal4) {
		ValorTotal4 = valorTotal4;
	}

	public void setBarModel(BarChartModel barModel) {
		this.barModel = barModel;
	}

}
