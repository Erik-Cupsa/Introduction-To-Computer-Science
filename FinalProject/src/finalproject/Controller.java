package finalproject;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Side;
import javafx.scene.chart.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class Controller {

	@FXML VBox root;
	@FXML VBox graphPane;
	@FXML TextField keywordInput;

	Random rng = new Random(260877596);
	DataAnalyzer analyzer;
	Parser parser;

	public void initialize() {
		System.out.println("init");

		System.out.print("parsing data...");
		parser = new Parser("/src/RateMyProf_Data_Gendered_Sample.csv");
		parser.read();

		System.out.println("done");
	}

	public void displayRatingByGenderPage() {
		graphPane.getChildren().clear();
		analyzer = new RatingByGender(parser);
		keywordInput.setOnKeyPressed( event -> {
			if( event.getCode() == KeyCode.ENTER ) {
				String dist = keywordInput.getText();
				drawLineGraph(keywordInput.getText(), dist, "Count", analyzer.getDistByKeyword("M,"+dist), analyzer.getDistByKeyword("F,"+dist));
			}
		} );
	}

	public void displayRatingByKeywordPage() {
		graphPane.getChildren().clear();
		analyzer = new RatingByKeyword(parser);
		keywordInput.setOnKeyPressed( event -> {
			if( event.getCode() == KeyCode.ENTER ) {
				drawLineGraph(keywordInput.getText(), "Rating", "Count", analyzer.getDistByKeyword(keywordInput.getText()));
			}
		} );
	}

	public void displayGenderByKeywordPage() {
		graphPane.getChildren().clear();
		analyzer = new GenderByKeyword(parser);
		keywordInput.setOnKeyPressed( event -> {
			if( event.getCode() == KeyCode.ENTER ) {
				drawBarGraph(keywordInput.getText(), "Gender", "Count", analyzer.getDistByKeyword(keywordInput.getText()));
			}
		} );
	}

	public void displayProfRatingsBySchoolPage() {
		graphPane.getChildren().clear();
		analyzer = new RatingDistributionBySchool(parser);
		keywordInput.setOnKeyPressed( event -> {
			if( event.getCode() == KeyCode.ENTER ) {
				drawBarGraph(keywordInput.getText(), "Prof", "Count", analyzer.getDistByKeyword(keywordInput.getText()));
			}
		} );
	}

	public void displayRatingsByProfPage() {
		graphPane.getChildren().clear();
		analyzer = new RatingDistributionByProf(parser);
		keywordInput.setOnKeyPressed( event -> {
			if( event.getCode() == KeyCode.ENTER ) {
				drawBarGraph(keywordInput.getText(), "Rating range", "Count", analyzer.getDistByKeyword(keywordInput.getText()));
			}
		} );
	}

	public void drawBarGraph(String title, String x, String y, MyHashTable<String,Integer> data) {
		if (data == null) return;
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final BarChart<String,Number> bc =
				new BarChart<String,Number>(xAxis,yAxis);
		bc.setTitle(title);
		xAxis.setLabel(x);
		yAxis.setLabel(y);

		XYChart.Series series1 = new XYChart.Series();

		// sort keys using their natural order
		ArrayList<String> keys = data.getKeySet();
		keys.sort(Comparator.naturalOrder());

		for (String key : keys) {
			series1.getData().add(new XYChart.Data(key, data.get(key)));
		}

		bc.setLegendSide(Side.BOTTOM);
		bc.setLegendVisible(false);
		bc.getData().addAll(series1);
		graphPane.getChildren().clear();
		graphPane.getChildren().add(bc);
	}

	public void drawLineGraph(String title, String x, String y, MyHashTable<String,Integer> data) {
		if (data == null) return;
		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart<String,Number> bc =
				new LineChart<>(xAxis,yAxis);
		bc.setTitle(title);
		xAxis.setLabel(x);
		yAxis.setLabel(y);

		XYChart.Series series1 = new XYChart.Series();

		for (String key : data.getKeySet()) {
			series1.getData().add(new XYChart.Data(key, data.get(key)));
		}

		bc.setLegendSide(Side.BOTTOM);
		bc.setLegendVisible(false);
		bc.getData().addAll(series1);
		graphPane.getChildren().clear();
		graphPane.getChildren().add(bc);
	}


	public void drawLineGraph(String title, String x, String y, MyHashTable<String,Integer> data1, MyHashTable<String,Integer> data2) {
		if (data1 == null || data2 == null) return;

		final CategoryAxis xAxis = new CategoryAxis();
		final NumberAxis yAxis = new NumberAxis();
		final LineChart<String,Number> bc =
				new LineChart<>(xAxis,yAxis);
		bc.setTitle(title);
		xAxis.setLabel(x);
		yAxis.setLabel(y);

		XYChart.Series series1 = new XYChart.Series();

		for (String key : data1.getKeySet()) {
			series1.getData().add(new XYChart.Data(key, data1.get(key)));
		}


		XYChart.Series series2 = new XYChart.Series();

		for (String key : data2.getKeySet()) {
			series2.getData().add(new XYChart.Data(key, data2.get(key)));
		}


		series1.setName("M");
		series2.setName("F");

		bc.setLegendSide(Side.BOTTOM);
		//bc.setLegendVisible(false);
		bc.getData().addAll(series1, series2);
		graphPane.getChildren().clear();
		graphPane.getChildren().add(bc);
	}


	public void drawPieGraph(String title, MyHashTable<String,Integer> data) {
		if (data == null) return;
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
		for (String key : data.getKeySet()) {
			pieChartData.add(new PieChart.Data(key, data.get(key)));
		}

		final PieChart chart = new PieChart(pieChartData);
		chart.setTitle(title);

		chart.setLegendVisible(false);

		graphPane.getChildren().clear();
		graphPane.getChildren().add(chart);
	}

}
