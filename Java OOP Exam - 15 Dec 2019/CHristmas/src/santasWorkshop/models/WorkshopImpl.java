package santasWorkshop.models;

import org.w3c.dom.ls.LSOutput;

public class WorkshopImpl implements Workshop {

    @Override
    public void craft(Present present, Dwarf dwarf) {

        while (!present.isDone() && dwarf.canWork() && !dwarf.getInstruments().isEmpty()) {
            for (Instrument instrument : dwarf.getInstruments()) {

                while (!instrument.isBroken() && !present.isDone()) {
                    instrument.use();
                    dwarf.work();

                    present.getCrafted();
                }
            }

        }

    }

}

