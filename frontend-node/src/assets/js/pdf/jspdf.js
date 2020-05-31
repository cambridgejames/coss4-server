import JSPDF from 'jspdf';

export default {
    name: 'jspdf',
    methods: {
        createPdf(competitionInfo) {
            let pdf = new JSPDF("p", "mm", "a4");
            pdf.setFontSize(20);
            pdf.text(20, 20, competitionInfo.compName);
            // return pdf.output("datauristring");
            return '/static/result_template.pdf';
        }
    }
}
