import org.apache.commons.cli.*;

public class CommandLine {

    static public void main(String[] args) {
        Options options = new Options();
        CommandLineParser parser = new DefaultParser();

        Option i = Option.builder("i").longOpt("input").required().hasArg().desc("Input file").build();
        Option o = Option.builder("o").longOpt("output").required().hasArg().desc("Output file").build();
        Option v = Option.builder("v").longOpt("verbose").required(false).hasArg(false).desc("Verbose").build();

        options.addOption(i);
        options.addOption(o);
        options.addOption(v);

        org.apache.commons.cli.CommandLine cmd;

        try {
            cmd = parser.parse(options, args);
            String infile = cmd.getOptionValue("input");
            String outfile = cmd.getOptionValue("output");
            boolean verbose = cmd.hasOption("verbose");

            System.out.println("Input filename: " + infile);
            System.out.println("Output filename: " + outfile);
            System.out.println("Verbose on: " + verbose);
        } catch (ParseException e) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("""
                    java -jar VCFCombine.jar
                    Version: 1
                    Program for combining a VCF and its Annovar annotation (.avinput) file.""", options);
        }

    }
}
