import java.net.*;
import java.io.*;
import net.sourceforge.jmodbus.*;

public class TempMonitor {

    public static void main(String[] args) {
        ServerSocket svrsocket = null;
        Socket socket = null;
        int unit_identifier = 0;
        Thread tempWorker;
        Thread modbWorker;
        ModbusTCPSlave modbus;
        int address_size;
        TemperatureWorker temp = new TemperatureWorker();
        address_size = temp.getNumberOfDevices();
        ModbusRegisterBank regs = new ModbusRegisterBank(address_size);
        temp.setModbusRegisters(regs);
        tempWorker = new Thread(temp);
        tempWorker.start();
        try {
            svrsocket = new ServerSocket(ModbusTCPTransport.MODBUS_TCP_PORT);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            return;
        }
        while (true) {
            try {
                socket = svrsocket.accept();
                modbus = new ModbusTCPSlave(unit_identifier, socket);
                modbus.setInputRegisters(regs);
                modbus.setOutputRegisters(regs);
                modbWorker = new Thread(modbus);
                modbWorker.start();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }
}
