package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;

public class ErrorLogger<Void> extends Task {
    @Override
    protected Void call() throws Exception {

        Platform.runLater(new Runnable() {
            @Override public void run() {

                Main.writelog(Main.filesArea, "Ошибка программы. Сообщи Анисимову.", true);

            }
        });

        return null;
    }
}
