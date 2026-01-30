package se.lunduniversity.kursregistrering.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import se.lunduniversity.kursregistrering.model.Course;
import se.lunduniversity.kursregistrering.model.Enrollment;
import se.lunduniversity.kursregistrering.model.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    @FXML private TableView<Enrollment> enrollmentTable;

    @FXML private TableColumn<Enrollment, String> studentCol;
    @FXML private TableColumn<Enrollment, String> courseCodeCol;
    @FXML private TableColumn<Enrollment, String> courseNameCol;
    @FXML private TableColumn<Enrollment, Double> creditsCol;
    @FXML private TableColumn<Enrollment, String> statusCol;

    @FXML private TextField studentIdField;
    @FXML private TextField firstNameField;
    @FXML private TextField lastNameField;
    @FXML private TextField programField;

    @FXML private TextField courseCodeField;
    @FXML private TextField courseNameField;
    @FXML private TextField creditsField;

    @FXML private ComboBox<String> statusCombo;

    @FXML private TextField searchField;

    private final ObservableList<Enrollment> enrollments = FXCollections.observableArrayList();
    private FilteredList<Enrollment> filtered;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        studentCol.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        courseCodeCol.setCellValueFactory(new PropertyValueFactory<>("courseCode"));
        courseNameCol.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        creditsCol.setCellValueFactory(new PropertyValueFactory<>("credits"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("status"));

        statusCombo.setItems(FXCollections.observableArrayList("Registered", "Completed", "Dropped"));
        statusCombo.getSelectionModel().select("Registered");

        // Exempeldata
        Student s1 = new Student(1, "Anna", "Andersson", "Systemvetenskap");
        Student s2 = new Student(2, "Erik", "Svensson", "Ekonomi");

        Course c1 = new Course("EDAA45", "Programmering", 7.5);
        Course c2 = new Course("NEK105", "Internationell ekonomi", 7.5);

        enrollments.addAll(
                new Enrollment(s1, c1, "Registered"),
                new Enrollment(s1, c2, "Completed"),
                new Enrollment(s2, c2, "Registered")
        );

        filtered = new FilteredList<>(enrollments, e -> true);
        enrollmentTable.setItems(filtered);

        // Sök/filter
        searchField.textProperty().addListener((obs, oldV, newV) -> {
            String q = (newV == null) ? "" : newV.trim().toLowerCase();

            filtered.setPredicate(e -> {
                if (q.isEmpty()) return true;

                return e.getStudentName().toLowerCase().contains(q)
                        || e.getCourseCode().toLowerCase().contains(q)
                        || e.getCourseName().toLowerCase().contains(q)
                        || e.getStatus().toLowerCase().contains(q);
            });
        });
    }

    @FXML
    private void handleAddEnrollment() {
        try {
            int id = Integer.parseInt(studentIdField.getText().trim());
            String firstName = firstNameField.getText().trim();
            String lastName = lastNameField.getText().trim();
            String program = programField.getText().trim();

            String code = courseCodeField.getText().trim();
            String courseName = courseNameField.getText().trim();
            double credits = Double.parseDouble(creditsField.getText().trim());

            String status = statusCombo.getValue();

            if (firstName.isEmpty() || lastName.isEmpty() || program.isEmpty()
                    || code.isEmpty() || courseName.isEmpty()) {
                showError("Alla fält (förutom status) måste fyllas i.");
                return;
            }

            Student student = new Student(id, firstName, lastName, program);
            Course course = new Course(code, courseName, credits);
            Enrollment enrollment = new Enrollment(student, course, status);

            enrollments.add(enrollment);
            clearForm();

        } catch (NumberFormatException e) {
            showError("Student-ID måste vara heltal och Hp måste vara ett tal (t.ex. 7.5).");
        }
    }

    @FXML
    private void handleRemoveSelected() {
        Enrollment selected = enrollmentTable.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showError("Markera en rad i tabellen först.");
            return;
        }
        enrollments.remove(selected);
    }

    private void clearForm() {
        studentIdField.clear();
        firstNameField.clear();
        lastNameField.clear();
        programField.clear();
        courseCodeField.clear();
        courseNameField.clear();
        creditsField.clear();
        statusCombo.getSelectionModel().select("Registered");
    }

    private void showError(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Fel");
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
}
