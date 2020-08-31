package santasWorkshop.core;

import santasWorkshop.common.ConstantMessages;
import santasWorkshop.common.ExceptionMessages;
import santasWorkshop.models.*;
import santasWorkshop.repositories.DwarfRepository;
import santasWorkshop.repositories.PresentRepository;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ControllerImpl implements Controller {

    private DwarfRepository dwarfRepository;
    private PresentRepository presentRepository;
    private Workshop workshop;

    public ControllerImpl() {
        this.dwarfRepository = new DwarfRepository();
        this.presentRepository = new PresentRepository();
        this.workshop = new WorkshopImpl();
    }

    @Override
    public String addDwarf(String type, String dwarfName) {
        try {
            Class<?> dwarfCLass = Class.forName("santasWorkshop.models." + type);
            Dwarf dwarf = (Dwarf) dwarfCLass.getConstructor(String.class).newInstance(dwarfName);
            this.dwarfRepository.add(dwarf);
            return String.format(ConstantMessages.ADDED_DWARF, type, dwarfName);

        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new IllegalArgumentException(ExceptionMessages.DWARF_TYPE_DOESNT_EXIST);

        }

    }

    @Override
    public String addInstrumentToDwarf(String dwarfName, int power) {
        if (this.dwarfRepository.findByName(dwarfName) == null) {
            throw new IllegalArgumentException(ExceptionMessages.DWARF_DOESNT_EXIST);
        } else {
            this.dwarfRepository.findByName(dwarfName).addInstrument(new InstrumentImpl(power));
        }

        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_INSTRUMENT_TO_DWARF, power, dwarfName);
    }

    @Override
    public String addPresent(String presentName, int energyRequired) {
        this.presentRepository.add(new PresentImpl(presentName, energyRequired));
        return String.format(ConstantMessages.SUCCESSFULLY_ADDED_PRESENT, presentName);
    }

    @Override
    public String craftPresent(String presentName) {
        int brokenInstrument = 0;
        Present presentToCraft = this.presentRepository.findByName(presentName);
        List<Dwarf> dwarfsForWork = this.dwarfRepository.
                getModels().stream().filter(e -> e.getEnergy() > 50).
                collect(Collectors.toList());
        if (dwarfsForWork.isEmpty()) {
            throw new IllegalArgumentException(ExceptionMessages.NO_DWARF_READY);
        }
        for (Dwarf dwarf : dwarfsForWork) {
            this.workshop.craft(presentToCraft, dwarf);
            for (Instrument instrument : dwarf.getInstruments()) {
                if (instrument.isBroken()) {
                    brokenInstrument++;
                }
            }
        }
        String presentCondititon;
        if (presentToCraft.isDone()) {
            presentCondititon = "done";
        } else {
            presentCondititon = "not done";
        }

        return String.format(ConstantMessages.PRESENT_DONE, presentName, presentCondititon) +
                String.format(ConstantMessages.COUNT_BROKEN_INSTRUMENTS, brokenInstrument);
    }

    @Override
    public String report() {
        int donePresents = this.presentRepository.getModels().stream().filter(Present::isDone).toArray().length;

        StringBuilder builder = new StringBuilder().append(donePresents).append(" presents are done!").append(System.lineSeparator());
        builder.append("Dwarfs info:").append(System.lineSeparator());
        for (Dwarf dwarf : this.dwarfRepository.getModels()) {
            builder.append(dwarf.toString());
        }

        return builder.toString().trim();
    }
}
