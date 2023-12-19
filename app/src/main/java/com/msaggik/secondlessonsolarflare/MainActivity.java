package com.msaggik.secondlessonsolarflare;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // поля
    private int[] delayMinute = {20, 42 , 85}; // задержка прихода сигнала с Марса, Юпитера, Сатурна (в минутах)

    private int[] volumeData = {14, 1459, 1251}; // количество информации в час с Марса, Юпитера, Сатурна

    private int coreFrequency = 3; // скорость компьютера в секунду

    private TextView output; // окно вывода на экран смартфона решения задачи

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка поля к разметке
        output = findViewById(R.id.output);

        // вывод данных на экран смартфона
        output.setText(coreTime(delayMinute, volumeData, coreFrequency) + " секунд");
    }

    // МОДУЛЬ 1
    // метод конвертирования времени задержки из минут в секунды (задержка в минутах)
    private int delaySecond(int delayMinute) {
        return delayMinute * 60;
    }

    // МОДУЛЬ 2
    // метод определения времени обработки компьютером данных с одной планеты в секундах (скорость компьютера, размер данных)
    private int volumeTime(int coreFrequency, int volumeData) {
        return volumeData / coreFrequency;
    }

    // МОДУЛЬ 3 (использующий МОДУЛИ 1-2)
    // метод вычисления времени обработки компьютером приходящей информации в секундах (массив задержек, массив объёмов данных, скорость компьютера)
    private int coreTime(int[] delayMinute, int[] volumeData, int frequency) {

        int[] delay = new int[delayMinute.length]; // создание пустого массива для задержек времени в секундах
        for (int i = 0; i < delay.length; i++) { // инициализация данного массива этого массива конвертированными данными (из минут в секунды)
            delay[i] = delaySecond(delayMinute[i]); // конвертация каждой ячейки массива из минут в секунды
        }

        int[] volume = new int[volumeData.length]; // создание пустого массива для времени обработки компьютером данных от спутника (в секундах)
        for (int i = 0; i < volume.length; i++) { // инициализация данного массива
            volume[i] = volumeTime(frequency, volumeData[i]); // определение времени обработки компьютером данных от одного спутника
        }

        int count = 0; // счётчик времени работы ядра компьютера
        int countOperation = 0; // счётчик количества подданных на ядро операций в секунду (например, обработка информации от двух спутников)
        boolean run = true; // флаг хода времени обработки данных со спутников

        while (run) { // бесконечный цикл работы ядра компьютера до прерывания

            count++; // повышение отсчёта времени на одну секунду
            run = false; // временное ограничение цикла while одним циклом

            for (int i = 0; i < delay.length; i++) { // с течением времени уменьшение задержки прихода информации со спутников
                if (delay[i] > 0) { // если от одного спутника сигнал ещё не дошёл, то
                    delay[i]--; // уменьшаем задержку на 1 секунду
                    run = true; // снятие ограничения на цикл while (так как в следующем цикле есть что обрабатывать)
                } else { // иначе обрабатываем данные со спутника
                    if (volume[i] > 0) { // если есть что обрабатывать, то
                        volume[i]--; // обрабатываем
                        countOperation++; // и повышаем счётчик занятости ядра компьютера
                        if (countOperation > 1) { // если в этот момент ядро уже было занято, то
                            count++; // прибавляем секунду к времени обработки ядром компьютера
                            countOperation--; // освобождаем ядро
                        }
                        run = true; // снятие ограничения на цикл while (так как в следующем цикле есть что обрабатывать)
                    } else { // иначе
                        // данные с данного спутника обработаны
                    }
                }
            }
        }

        return count; // возврат времени обработки данных от спутников (в секундах)
    }
}