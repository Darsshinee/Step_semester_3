final class CriticalCareDischargeSummary extends DischargeSummary {
    private final int icuDays;

    public CriticalCareDischargeSummary(String patientId,
                                         String[] medicationCodes,
                                         int icuDays) {
        super(patientId, medicationCodes);
        this.icuDays = icuDays;
    }
}

public final class DischargeSummary {
    private final String patientId;
    private final String[] medicationCodes;

    static int totalProcessed;

    static {
        totalProcessed = 0;
    }

    public DischargeSummary(String patientId, String[] medicationCodes) {
        if (medicationCodes == null) {
            throw new IllegalArgumentException("Invalid codes");
        }

        for (String code : medicationCodes) {
            if (code == null ||
                !code.matches("MED-[A-Z]")) {
                throw new IllegalArgumentException("Invalid medication code");
            }
        }

        this.patientId = patientId;
        this.medicationCodes = medicationCodes.clone();
    }

    String[] getMedicationCodes() {
        return medicationCodes.clone();
    }

    DischargeSummary withCorrectedMedication(int index,
                                             String newCode) {
        if (!newCode.matches("MED-[A-Z]")) {
            throw new IllegalArgumentException("Invalid medication");
        }

        String[] copy = medicationCodes.clone();
        copy[index] = newCode;

        return new DischargeSummary(patientId, copy);
    }

    static String processNightlyBatch(DischargeSummary[] summaries) {
        int processed = 0;
        int nullSkipped = 0;
        int critical = 0;
        int routine = 0;

        for (DischargeSummary summary : summaries) {
            if (summary == null) {
                nullSkipped++;
                continue;
            }

            processed++;

            if (summary instanceof CriticalCareDischargeSummary) {
                critical++;
            } else {
                routine++;
            }
        }

        return processed + " processed | " +
                nullSkipped + " null skipped | " +
                critical + " critical-care | " +
                routine + " routine";
    }

    public static void main(String[] args) {
        DischargeSummary[] summaries = {
            new CriticalCareDischargeSummary(
                    "MT001", new String[]{"MED-X"}, 4),
            null,
            new DischargeSummary(
                    "MT002", new String[]{"MED-Y"})
        };

        System.out.println(processNightlyBatch(summaries));
    }
}