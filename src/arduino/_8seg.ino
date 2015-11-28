/**
 * Reads a value from the serial port, shows it in a 4 digit 8-segment display
 */

const int a=3;
const int b=4;
const int c=5;
const int d=6;
const int e=7;
const int f=8;
const int g=9;
const int d1=A3;
const int d2=A2;
const int d3=A1;
const int d4=A0;

// the setup function runs once when you press reset or power the board
void setup() {
  Serial.begin(9600);
  pinMode(a, OUTPUT);
  pinMode(b, OUTPUT);
  pinMode(c, OUTPUT);
  pinMode(d, OUTPUT);
  pinMode(e, OUTPUT);
  pinMode(f, OUTPUT);
  pinMode(g, OUTPUT);
  pinMode(d1, OUTPUT);
  pinMode(d2, OUTPUT);
  pinMode(d3, OUTPUT);
  pinMode(d4, OUTPUT);

}
int i = 0;
int val = 0;
void loop() {
  int tmp = Serial.read();
  if(tmp != 0){
    val = tmp;
  }
  write(val);
  delay(2);
}

void writeRandom(){
  if(i++ % 10 == 0) {
    val = random(9999);
  }
  write(val);
  delay(10);
}

void write(int num) {
  int n1 = num % 10;
  int n2 = (num /10) % 10;
  int n3 = (num /100) % 10;
  int n4 = (num /1000) % 10;

  onDigit(d1);
  writeSingleDigit(n4);
  delay(3);
  onDigit(d2);
  writeSingleDigit(n3);
  delay(3);
  onDigit(d3);
  writeSingleDigit(n2);
  delay(3);
  onDigit(d4);
  writeSingleDigit(n1);
  delay(3);
}

void writeSingleDigit(int n){
  switch(n) {
    case 0:
      up(a); up(b); up(c); up(d); up(e); up(f); down(g);
      break;
    case 1:
      down(a); up(b); up(c); down(d); down(e); down(f); down(g);
      break;
    case 2:
      up(a); up(b); down(c); up(d); up(e); down(f); up(g);
      break;
    case 3:
      up(a); up(b); up(c); up(d); down(e); down(f); up(g);
      break;
    case 4:
      down(a); up(b); up(c); down(d); down(e); up(f); up(g);
      break;
    case 5:
      up(a); down(b); up(c); up(d); down(e); up(f); up(g);
      break;
    case 6:
      down(a); down(b); up(c); up(d); up(e); up(f); up(g);
      break;
    case 7:
      up(a); up(b); up(c); down(d); down(e); down(f); down(g);
      break;
    case 8:
      up(a); up(b); up(c); up(d); up(e); up(f); up(g);
      break;
    case 9:
      up(a); up(b); up(c); up(d); down(e); up(f); up(g);
      break;
  }
}

void up(int segment) {
  digitalWrite(segment, HIGH);
}

void down(int segment) {
  digitalWrite(segment, LOW);
}

void onDigit(int digit)  {
  analogWrite(d1, 1023);
  analogWrite(d2, 1023);
  analogWrite(d3, 1023);
  analogWrite(d4, 1023);
  analogWrite(digit, 0);
}

void onAll(){
  digitalWrite(a, HIGH);  
  digitalWrite(b, HIGH);  
  digitalWrite(c, HIGH);  
  digitalWrite(d, HIGH);  
  digitalWrite(e, HIGH);  
  digitalWrite(f, HIGH);  
  digitalWrite(g, HIGH);  
}


void offAll(){
  digitalWrite(a, LOW);  
  digitalWrite(b, LOW);  
  digitalWrite(c, LOW);  
  digitalWrite(d, LOW);  
  digitalWrite(e, LOW);  
  digitalWrite(f, LOW);  
  digitalWrite(g, LOW);  
}


void doCycle(){
  digitalWrite(g, LOW);  
  digitalWrite(a, HIGH);  
  delay(1000);
  digitalWrite(a, LOW);  
  digitalWrite(b, HIGH);  
  delay(1000);
  digitalWrite(b, LOW);  
  digitalWrite(c, HIGH);  
  delay(1000);              // wait for a second
  digitalWrite(c, LOW);  
  digitalWrite(d, HIGH);  
  delay(1000);              // wait for a second
  digitalWrite(d, LOW);  
  digitalWrite(e, HIGH);  
  delay(1000);              // wait for a second
  digitalWrite(e, LOW);  
  digitalWrite(f, HIGH);  
  delay(1000);              // wait for a second
  digitalWrite(f, LOW);  
  digitalWrite(g, HIGH);  
  delay(1000);            
}

 
