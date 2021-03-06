package net.bohush.exercises.chapter33;

import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import javax.swing.*;

public class Exercise01s extends JFrame {
	private static final long serialVersionUID = 1L;

	private JTextArea jta = new JTextArea();

	public static void main(String[] args) {
		new Exercise01s();
	}

	public Exercise01s() {
		setLayout(new BorderLayout());
		add(new JScrollPane(jta), BorderLayout.CENTER);
		setTitle("Exercise01s");
		setSize(500, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(8000);
			jta.append("Server started at " + new Date() + '\n');

			Socket socket = serverSocket.accept();

			DataInputStream inputFromClient = new DataInputStream(socket.getInputStream());
			DataOutputStream outputToClient = new DataOutputStream(socket.getOutputStream());

			while (true) {
				double interest = inputFromClient.readDouble();
				int year = inputFromClient.readInt();
				double loanAmount = inputFromClient.readDouble();

				Loan loan = new Loan(interest, year, loanAmount);
				double monthlyPayment = loan.getMonthlyPayment();
				double totalPayment = loan.getTotalPayment();
				
				outputToClient.writeDouble(monthlyPayment);
				outputToClient.writeDouble(totalPayment);
				
				jta.append("Annual Interest Rate " + interest + "\n");
				jta.append("Number Of Years " + year + "\n");
				jta.append("Loan Amount " + loanAmount + "\n");
				jta.append("monthlyPayment " + monthlyPayment + "\n");
				jta.append("totalPayment " + totalPayment + "\n");
				
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
		
	}
	
	
	class Loan {
		private double annualInterestRate;
		private int numberOfYears;
		private double loanAmount;
		private java.util.Date loanDate;

		/** Default constructor */
		public Loan() {
			this(2.5, 1, 1000);
		}

		/**
		 * Construct a loan with specified annual interest rate, number of
		 * years, and loan amount
		 */
		public Loan(double annualInterestRate, int numberOfYears,
				double loanAmount) {
			this.annualInterestRate = annualInterestRate;
			this.numberOfYears = numberOfYears;
			this.loanAmount = loanAmount;
			loanDate = new java.util.Date();
		}

		/** Return annualInterestRate */
		public double getAnnualInterestRate() {
			return annualInterestRate;
		}

		/** Set a new annualInterestRate */
		public void setAnnualInterestRate(double annualInterestRate) {
			this.annualInterestRate = annualInterestRate;
		}

		/** Return numberOfYears */
		public int getNumberOfYears() {
			return numberOfYears;
		}

		/** Set a new numberOfYears */
		public void setNumberOfYears(int numberOfYears) {
			this.numberOfYears = numberOfYears;
		}

		/** Return loanAmount */
		public double getLoanAmount() {
			return loanAmount;
		}

		/** Set a newloanAmount */
		public void setLoanAmount(double loanAmount) {
			this.loanAmount = loanAmount;
		}

		/** Find monthly payment */
		public double getMonthlyPayment() {
			double monthlyInterestRate = annualInterestRate / 1200;
			double monthlyPayment = loanAmount
					* monthlyInterestRate
					/ (1 - (1 / Math.pow(1 + monthlyInterestRate,
							numberOfYears * 12)));
			return monthlyPayment;
		}

		/** Find total payment */
		public double getTotalPayment() {
			double totalPayment = getMonthlyPayment() * numberOfYears * 12;
			return totalPayment;
		}

		/** Return loan date */
		public java.util.Date getLoanDate() {
			return loanDate;
		}
	}
}
