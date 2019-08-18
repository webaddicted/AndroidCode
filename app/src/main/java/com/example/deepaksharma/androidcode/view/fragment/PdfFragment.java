package com.example.deepaksharma.androidcode.view.fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.databinding.ViewDataBinding;

import com.example.deepaksharma.androidcode.R;
import com.example.deepaksharma.androidcode.databinding.FragmentPdfBinding;
import com.example.deepaksharma.androidcode.utils.GlobalUtilities;
import com.example.deepaksharma.androidcode.view.base.BaseFragment;
import com.example.deepaksharma.androidcode.view.home.HomeActivity;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.html.WebColors;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class PdfFragment extends BaseFragment {
    public static final String TAG = PdfFragment.class.getSimpleName();
    private FragmentPdfBinding mBinding;
    public static PdfFragment getInstance(Bundle bundle) {
        PdfFragment fragment = new PdfFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_pdf;
    }

    @Override
    protected void onViewsInitialized(ViewDataBinding binding, View view) {
        mBinding = (FragmentPdfBinding) binding;
        init();
        clickListener();
    }

    private void init() {

    }

    private void clickListener() {
        mBinding.btnCreatePdf.setOnClickListener(this);
        mBinding.btnSavePdf.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()) {
            case R.id.btn_create_pdf:
                try {
                    createPDF();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (DocumentException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_save_pdf:
                openPdf();
                break;

        }
    }

    @Override
    public void onResume() {
        super.onResume();
        ((HomeActivity) getActivity()).showBackBtn();
        ((HomeActivity) getActivity()).setToolbarTitle(getResources().getString(R.string.animation_title));
    }
    private String path;
    private File dir;
    private File file;
    String pdfName;
    private PdfPCell cell;
    private Image bgImage;
    BaseColor myColor = WebColors.getRGBColor("#9E9E9E");
    BaseColor myColor1 = WebColors.getRGBColor("#757575");
    public void createPDF() throws FileNotFoundException, DocumentException {

        //create document file
        Document doc = new Document();
        try {

            Log.e("PDFCreator", "PDF Path: " + path);
            SimpleDateFormat sdf = new SimpleDateFormat("ddMMyyyy");
            pdfName= "Raj" +sdf.format(Calendar.getInstance().getTime()) + ".pdf";
//            pdfName= "Raj " +System.currentTimeMillis() + ".pdf";
            file = new File(dir,pdfName );
            FileOutputStream fOut = new FileOutputStream(file);
            PdfWriter writer = PdfWriter.getInstance(doc, fOut);

            //open the document
            doc.open();
//create table
            PdfPTable pt = new PdfPTable(3);
            pt.setWidthPercentage(100);
            float[] fl = new float[]{20, 45, 35};
            pt.setWidths(fl);
            cell = new PdfPCell();
            cell.setBorder(Rectangle.NO_BORDER);

            //set drawable in cell
            Drawable myImage = getResources().getDrawable(R.drawable.logo);
            Bitmap bitmap = ((BitmapDrawable) myImage).getBitmap();
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] bitmapdata = stream.toByteArray();
            try {
                bgImage = Image.getInstance(bitmapdata);
                bgImage.setAbsolutePosition(330f, 642f);
                cell.addElement(bgImage);
                pt.addCell(cell);
                cell = new PdfPCell();
                cell.setBorder(Rectangle.NO_BORDER);
                //    Heading name ur Firm Name
                cell.addElement(new Paragraph(getResources().getString(R.string.app_name)));

                cell.addElement(new Paragraph(""));
                cell.addElement(new Paragraph(""));
                pt.addCell(cell);
                cell = new PdfPCell(new Paragraph(""));
                cell.setBorder(Rectangle.NO_BORDER);
                pt.addCell(cell);

                PdfPTable pTable = new PdfPTable(1);
                pTable.setWidthPercentage(100);
                cell = new PdfPCell();
                cell.setColspan(1);
                cell.addElement(pt);
                pTable.addCell(cell);
                // no Of Column no a field
                PdfPTable table = new PdfPTable(7);
//width of  every coloumn
                float[] columnWidth = new float[]{12, 30, 30, 20, 20, 30, 30};
                table.setWidths(columnWidth);


                cell = new PdfPCell();


                cell.setBackgroundColor(myColor);
                cell.setColspan(7);
                cell.addElement(pTable);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase(" "));
                cell.setColspan(7);
                table.addCell(cell);
                cell = new PdfPCell();
                cell.setColspan(7);

                cell.setBackgroundColor(myColor1);
// colom  Heading name Defile Here
                cell = new PdfPCell(new Phrase("Sr No."));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Date"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Particulars"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Voucher Type"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Voucher No."));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Debit"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);
                cell = new PdfPCell(new Phrase("Credit"));
                cell.setBackgroundColor(myColor1);
                table.addCell(cell);


                //table.setHeaderRows(3);
                cell = new PdfPCell();
                cell.setColspan(7);
//                custDate.setText("19/07/2017" + " " + "to");
//                custPart.setText("ICICI Bank 12355252255455");
//                custVchTyp.setText("Sales");
//                custVchNo.setText("25");
//                custCredit.setText("25000");
//                custDebit.setText("40000");

                for (int i = 1; i <= 8; i++) {
                    table.addCell(String.valueOf(i));
                    table.addCell("19/07/2017" + " " + "to");
                    table.addCell("ICICI Bank 12355252255455");
                    table.addCell("Sales");
                    table.addCell("25");
                    table.addCell("25000");
                    table.addCell("40000");

                }

                PdfPTable ftable = new PdfPTable(7);
                ftable.setWidthPercentage(100);
//                float[] columnWidthaa = new float[]{30, 10, 30, 10, 30, 10};
                float[] columnWidthaa = new float[]{92, 0, 0, 0, 20, 30, 30};

                ftable.setWidths(columnWidthaa);
                cell = new PdfPCell();
                cell.setColspan(7);
                cell.setBackgroundColor(myColor1);
                cell = new PdfPCell(new Phrase("Total Amount"));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase(""));
                cell.setBorder(Rectangle.NO_BORDER);
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
                cell = new PdfPCell(new Phrase("320000"));
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);

                cell = new PdfPCell(new Phrase("200000"));
                cell.setBackgroundColor(myColor1);
                ftable.addCell(cell);
//                cell = new PdfPCell(new Phrase(totalDedit+""));
//                cell.setBorder(Rectangle.NO_BORDER);
//                cell.setBackgroundColor(myColor1);
//                ftable.addCell(cell);
                cell = new PdfPCell(new Paragraph(""));
                cell.setColspan(7);
                ftable.addCell(cell);
                cell = new PdfPCell();
                cell.setColspan(7);
                cell.addElement(ftable);
                table.addCell(cell);
                doc.add(table);
                GlobalUtilities.showToast("Success PDF is Sucessfully Generated");
                openPdf();
            } catch (DocumentException de) {
                Log.e("PDFCreator", "DocumentException:" + de);
            } catch (IOException e) {
                Log.e("PDFCreator", "ioException:" + e);
            } finally {
                doc.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void openPdf() {
        String pathName =path+"/" + pdfName;// arrayStatements.get(i).getImageName();

        Log.d("aaaaaaaaaaa       ", "pathName--->" + pathName);
        File file = new File(pathName);
        Log.d("aaaaaaaaaaaaaaaaa","file===>"+file);
        if (file.exists()) {
            Uri uriii = Uri.fromFile(file);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uriii, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
    }
}
