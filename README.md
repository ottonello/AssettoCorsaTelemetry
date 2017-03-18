# AssettoCorsaTelemetry

Includes a Java program to read data from Assetto Corsa's UDP interface, and send speed data to a serial port,
and the Arduino program to receive data from the serial port and display a speed into an 8-segment display.

The Java program uses [RXTX](http://jlog.org/rxtx-win.html) to connect to the Arduino's serial interface.
It connects to Assetto Corsa on port 9999, and uses Guava's LittleEndianDataInputStream to read data from the UDP stream as C structs.
