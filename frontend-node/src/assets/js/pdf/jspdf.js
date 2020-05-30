import JSPDF from 'jspdf';

export default {
    name: 'jspdf',
    methods: {
        createPdf(competitionInfo) {
            let pdf = new JSPDF("p", "mm", "a4");
            pdf.setFont("FZYTK");
            pdf.setFontSize(20);
            pdf.text(competitionInfo.compName, 55, 180);
            return pdf.output("datauristring");
        }
    }
}
