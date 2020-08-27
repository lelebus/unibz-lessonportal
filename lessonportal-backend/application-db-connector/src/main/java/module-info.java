module sqlConnector {
	exports it.unibz.dbConnector;

	requires c3p0;
	requires java.desktop;
	requires java.naming;
	requires java.sql;
}