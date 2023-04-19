package notebook;

import lombok.Getter;

@Getter
public class Notebook {
    private final int id;
    String brand;
    String model;
    String condition;
    String color;
    int screenSize;
    String modelCPU;
    String modelGPU;
    int amountRAM;
    int amountHUD;

    public Notebook(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return String.format("""
                 %s %s %s
                 Состояние: %s
                 Диагональ %d дюймов Процессор: %s Видеокарта: %s Объём RAM: %s ГБ Объём HUD %s ГБ
                """, brand, model, color, condition, screenSize, modelCPU, modelGPU, amountRAM, amountHUD);
    }

}