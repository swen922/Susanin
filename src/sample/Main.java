package sample;


import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.control.CheckBox;

import java.io.File;
import java.nio.file.Path;
import java.util.*;





public class Main extends Application {

    // Основные переменные:
    private Text dropFilesHere = new Text("Напиши в верхнем окошке слова, которые ищем:");
    private TextArea findArea = new TextArea();
    public static TextArea filesArea = new TextArea("Тащи в это окошко папочки, где искать");
    private Button searchButton = new Button("СТАРТ / СТОП");
    private Button resetButton = new Button("Сбросить поиск");
    public static CheckBox checkBoxFolders = new CheckBox("Искать во внутренних папках");
    public static CheckBox checkBoxFoldersOnly = new CheckBox("Искать только папки");




    @Override
    public void start(Stage primaryStage) throws Exception{
        //Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));

        // Основные свойства переменных:
        dropFilesHere.setX(25);
        dropFilesHere.setY(25);
        dropFilesHere.setFill(Color.rgb(20, 120, 160));
        dropFilesHere.setFont(Font.font(null, FontWeight.BLACK, 18));

        findArea.setWrapText(true);
        findArea.setPrefSize(550, 50);
        findArea.setLayoutX(25);
        findArea.setLayoutY(35);
        findArea.setFont(Font.font(null, FontWeight.BOLD, 18));

        filesArea.setWrapText(true);
        filesArea.setMinSize(550, 200);
        filesArea.setLayoutX(25);
        filesArea.setLayoutY(100);

        searchButton.setPrefSize(300,60);
        searchButton.setLayoutX(275);
        searchButton.setLayoutY(340);
        searchButton.setFont(Font.font(null, FontWeight.BOLD, 18));

        resetButton.setPrefSize(200, 30);
        resetButton.setLayoutX(25);
        resetButton.setLayoutY(340);

        checkBoxFolders.setLayoutX(275);
        checkBoxFolders.setLayoutY(310);
        checkBoxFolders.setSelected(true);

        checkBoxFoldersOnly.setLayoutX(25);
        checkBoxFoldersOnly.setLayoutY(310);
        checkBoxFoldersOnly.setSelected(false);


        // Основные функции он дроп для всех полей
        filesArea.setOnDragOver(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard board = event.getDragboard();
                if (board.hasFiles()) {
                    event.acceptTransferModes(TransferMode.ANY);
                }
            }
        });
        filesArea.setOnDragDropped(new EventHandler<DragEvent>() {
            @Override
            public void handle(DragEvent event) {
                Dragboard board = event.getDragboard();
                List<File> dropedFiles = board.getFiles();

                for (File f : dropedFiles) {
                    Path p = f.toPath();
                    if (!Controller.listFiles.contains(p)) {
                        Controller.listFiles.add(p);
                    }
                }
                filesArea.setText("");
                for (Path p : Controller.listFiles) {
                    writelog(filesArea, p.toAbsolutePath().toString(), false);
                }
            }
        });

        findArea.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                findArea.setText("");
            }
        });

        searchButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if ((Controller.searchThread == null) || (!Controller.searchThread.isAlive())) {

                    Controller.result.clear();
                    Controller.counterAllFiles.clear();
                    Controller.answer = null;

                    if ((findArea.getText() == null) || (findArea.getText().isEmpty())) {
                        writelog(findArea, "Эй, аллё, а чё ищем-то? Слова давай сначала!", true);
                    }
                    else if (Controller.listFiles.isEmpty()) {
                        writelog(filesArea, "А сюда надо файло затащить, написано жеж!", true);
                    }
                    else {
                        Controller.searchString = findArea.getText();
                        Controller.search();
                    }
                }
                else {

                    Controller.parallelSearch.cancel();
                    //Controller.finalizeSearch();
                }

            }
        });
        resetButton.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (Controller.parallelSearch != null) {
                    Controller.parallelSearch.cancel();
                }
                if ((Controller.searchThread != null) && (Controller.searchThread.isAlive())) {
                    Controller.searchThread.interrupt();
                }
                findArea.setText("");
                filesArea.setText("Тащи в это окошко папочки, где искать");
                Controller.listFiles.clear();
                Controller.result.clear();

            }
        });




        // Создаем root, сцену и запускаем все хозяйство:
        Group root = new Group();

        primaryStage.setTitle("Ищем файло на серваке");
        primaryStage.setScene(new Scene(root, 600, 410));

        root.getChildren().addAll(findArea, filesArea, dropFilesHere, searchButton, resetButton, checkBoxFolders, checkBoxFoldersOnly);

        primaryStage.show();
    }


    public static void writelog(TextArea textArea, String text, boolean replace) {
        if (replace) {
            textArea.setText("");
            textArea.appendText(text);
        }
        else {
            textArea.appendText("\n" + text + "\n");
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
