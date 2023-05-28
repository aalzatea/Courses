export interface Driver {
  database: string;
  password: string;
  port: number;

  connect(): void;
  disconnect(): void;
  isConnected(): boolean;
}

class PostgresDriver implements Driver {

  private connected: boolean = false;

  constructor(
    public database: string,
    public password: string,
    public port: number){
  }

  connect(): void {
    this.connected = true;
    console.log('Connected to Postgres');
  }

  disconnect(): void {
    this.connected = false;
    console.log('Disconnected from Postgres');
  }

  isConnected(): boolean {
      return this.connected;
  }
}

class OracleDriver implements Driver {

  private connected: boolean = false;

  constructor(
    public database: string,
    public password: string,
    public port: number){
  }

  connect(): void {
    this.connected = true;
    console.log('Connected to Oracle');
  }

  disconnect(): void {
    this.connected = false;
    console.log('Disconnected from Oracle');
  }

  isConnected(): boolean {
    return this.connected;
  }
}

const postgresDriver: Driver = new PostgresDriver('postgres', 'postgres', 1234);
postgresDriver.connect();

const oracleDriver: Driver = new OracleDriver('oracle', 'oracle', 5126);
oracleDriver.connect();
