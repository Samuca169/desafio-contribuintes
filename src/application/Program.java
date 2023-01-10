package application;

import java.util.Locale;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);

		System.out.println("Digite os dados do contribuinte:");
		System.out.print("Renda anual com salario: ");
		double salario =sc.nextDouble();
		System.out.print("Renda anual com prestação de serviço: ");
		double prestacaoDeServico = sc.nextDouble();
		System.out.print("Renda anual com gamho de capital: ");
		double ganhoCapital =sc.nextDouble();
		System.out.print("Gastos médicos: ");
		double gastosMedicos = sc.nextDouble();
		System.out.print("Gastos educacionais: ");
		double gastosEducacionais = sc.nextDouble();
		
		double impSalario = impostoSobreSalario(salario);
		double impServico = impostoSobreServico(prestacaoDeServico);
		double impostoGC = impostoSobreGC(ganhoCapital);
		double impBrutoTotal = impostoBrutoTotal(salario, prestacaoDeServico, ganhoCapital);
		double abatimento = abatimentos(salario, prestacaoDeServico, ganhoCapital, gastosMedicos, gastosEducacionais);
		double impostoDevido = impBrutoTotal - abatimento;
		
		System.out.println();
		System.out.println("RELATÓRIO:");
		System.out.println("Imposto sobre salário: " + String.format("%.2f", impSalario));
		System.out.println("Imposto sobre serviço: " + String.format("%.2f", impServico));
		System.out.println("Imposto sobre ganho capital: " + String.format("%.2f", impostoGC));
		System.out.println("Imposto bruto total: " + String.format("%.2f", impBrutoTotal));
		System.out.println("Abatimentos: " + String.format("%.2f", abatimento));
		System.out.println("Imposto devido: " + String.format("%.2f", impostoDevido));
		sc.close();
	}
	
	public static double impostoSobreSalario(double quantia) {
		double imposto = 0.0;
		if (quantia / 12 >= 3000.00 && quantia / 12 <= 5000.00) {
			imposto = quantia * 0.10;	
		}else {
			if (quantia / 12 > 5000.00) {
				imposto = quantia * 0.20;
			}
		}
		return imposto;
	}
	
	public static double impostoSobreServico(double quantia) {
		double imposto = quantia * 0.15;
		return imposto;
	}
	
	public static double impostoSobreGC(double quantia) {
		double imposto = quantia * 0.20;
		return imposto;
	}
	
	public static double impostoBrutoTotal(double salario, double servico, double ganhoCapital) {
		double impSalario = impostoSobreSalario(salario);
		double impServico = impostoSobreServico(servico);
		double impGC = impostoSobreGC(ganhoCapital);
		double imposto = impSalario + impServico + impGC; 
		return imposto;
	}
	
	public static double abatimentos(double salario, double servico, double ganhoCapital, double gastosMedicos, double gastosEducacionais) {
		double impTotal = impostoBrutoTotal(salario, servico, ganhoCapital);
		double gastos = gastosMedicos + gastosEducacionais;
		double abatimento = 0.0;
		if (gastos > (impTotal * 0.30)) {
			abatimento = impTotal * 0.30;
		}else {
			abatimento = gastos;
		}
		return abatimento;
	}
}
