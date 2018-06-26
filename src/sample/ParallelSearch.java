package sample;

import javafx.application.Platform;
import javafx.concurrent.Task;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class ParallelSearch<Void> extends Task {

    @Override
    protected Void call() throws Exception {

        if ((!Main.checkBoxFoldersOnly.isSelected()) && (Main.checkBoxFolders.isSelected())) {

            for (Path path : Controller.listFiles) {

                try {

                    Files.walkFileTree(path, new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                            boolean containAll = true;
                            for (String s : Controller.searchStrings) {
                                if (!file.getFileName().toString().toLowerCase().contains(s.toLowerCase())) {
                                    containAll = false;
                                }
                            }

                            if ((containAll) && (!file.getFileName().toString().startsWith("."))) {
                                Controller.result.add(file);
                            }

                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.writelog(Main.filesArea, status, true);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }

                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                            boolean containAll = true;
                            for (String s : Controller.searchStrings) {
                                if (!dir.getFileName().toString().toLowerCase().contains(s.toLowerCase())) {
                                    containAll = false;
                                }
                            }
                            if ((containAll) && (!dir.getFileName().toString().startsWith("."))) {
                                Controller.result.add(dir);
                            }
                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.filesArea.setText(status);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }

                            return FileVisitResult.CONTINUE;
                        }


                    });
                } catch (IOException e) {
                    Controller.errorLog();
                }
            }
        }
        else if ((!Main.checkBoxFoldersOnly.isSelected()) && (!Main.checkBoxFolders.isSelected())) {
            for (Path path : Controller.listFiles) {

                try {

                    Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 1, new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                            boolean containAll = true;
                            for (String s : Controller.searchStrings) {
                                if (!file.getFileName().toString().toLowerCase().contains(s.toLowerCase())) {
                                    containAll = false;
                                }
                            }

                            if ((containAll) && (!file.getFileName().toString().startsWith("."))) {
                                Controller.result.add(file);
                            }

                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.filesArea.setText(status);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }

                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                            boolean containAll = true;
                            for (String s : Controller.searchStrings) {
                                if (!dir.getFileName().toString().toLowerCase().contains(s.toLowerCase())) {
                                    containAll = false;
                                }
                            }
                            if ((containAll) && (!dir.getFileName().toString().startsWith("."))) {
                                Controller.result.add(dir);
                            }
                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.filesArea.setText(status);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }

                            return FileVisitResult.CONTINUE;
                        }


                    });
                } catch (IOException e) {
                    Controller.errorLog();
                }
            }
        }
        else if ((Main.checkBoxFoldersOnly.isSelected()) && (Main.checkBoxFolders.isSelected())) {
            for (Path path : Controller.listFiles) {

                try {

                    Files.walkFileTree(path, new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.filesArea.setText(status);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }
                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                            boolean containAll = true;
                            for (String s : Controller.searchStrings) {
                                if (!dir.getFileName().toString().toLowerCase().contains(s.toLowerCase())) {
                                    containAll = false;
                                }
                            }
                            if ((containAll) && (!dir.getFileName().toString().startsWith("."))) {
                                Controller.result.add(dir);
                            }
                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.filesArea.setText(status);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }

                            return FileVisitResult.CONTINUE;
                        }


                    });
                } catch (IOException e) {
                    Controller.errorLog();
                }
            }
        }
        else if ((Main.checkBoxFoldersOnly.isSelected()) && (!Main.checkBoxFolders.isSelected())) {
            for (Path path : Controller.listFiles) {

                try {

                    Files.walkFileTree(path, EnumSet.noneOf(FileVisitOption.class), 2, new SimpleFileVisitor<>() {
                        @Override
                        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.filesArea.setText(status);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }

                            return FileVisitResult.CONTINUE;
                        }

                        @Override
                        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {

                            boolean containAll = true;
                            for (String s : Controller.searchStrings) {
                                if (!dir.getFileName().toString().toLowerCase().contains(s.toLowerCase())) {
                                    containAll = false;
                                }
                            }
                            if ((containAll) && (!dir.getFileName().toString().startsWith("."))) {
                                Controller.result.add(dir);
                            }
                            Controller.counterAllFiles.add((byte)1);

                            String status = String.format("Обработано объектов: %d\nНайдено соответствий: %d\n", Controller.counterAllFiles.size(), Controller.result.size());
                            Platform.runLater(new Runnable() {
                                @Override public void run() {
                                    Main.filesArea.setText(status);
                                }
                            });

                            if (Controller.parallelSearch.isCancelled()) {
                                return FileVisitResult.TERMINATE;
                            }

                            return FileVisitResult.CONTINUE;
                        }


                    });
                } catch (IOException e) {
                    Controller.errorLog();
                }
            }
        }

        Controller.finalizeSearch();

        return null;
    }
    // end of call() method
}
