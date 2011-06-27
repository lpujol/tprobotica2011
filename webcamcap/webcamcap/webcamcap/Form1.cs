using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Text;
using System.Windows.Forms;
using System.IO;
using System.Drawing.Imaging;
using WebCam_Capture;

namespace webcamcap
{
    
    public partial class Form1 : Form
    {
        WebCam_Capture.WebCamCapture wc1;
        WebCam_Capture.WebCamCapture wc2;
        bool captura1 = false;
        bool captura2 = false;

        public Form1()
        {
            wc1 = new WebCamCapture();
            wc2 = new WebCamCapture();
            wc1.ImageCaptured += new WebCamCapture.WebCamEventHandler(wc1_ImageCaptured);
            wc2.ImageCaptured += new WebCamCapture.WebCamEventHandler(wc2_ImageCaptured);
            InitializeComponent();
        }

        void wc2_ImageCaptured(object source, WebcamEventArgs e)
        {
            if (captura2)
            {
                e.WebCamImage.Save("camara2_" + DateTime.Now.ToString("hh_mm_ss") + ".jpg", ImageFormat.Jpeg);
                captura2 = false;
                if(!captura1) MessageBox.Show("Imagenes guardada satisfactoriamente");
            }
            pictureBox2.Image=e.WebCamImage;
        }

        void wc1_ImageCaptured(object source, WebcamEventArgs e)
        {
            if (captura1)
            {
                e.WebCamImage.Save("camara1_" + DateTime.Now.ToString("hh_mm_ss") + ".jpg", ImageFormat.Jpeg);
                captura1 = false;
                if (!captura2) MessageBox.Show("Imagenes guardada satisfactoriamente");
            }
            pictureBox1.Image = e.WebCamImage;
        }

        private void Form1_Load(object sender, EventArgs e)
        {
            wc1.CaptureHeight = 768;
            wc1.CaptureWidth = 1024;
            wc2.CaptureHeight = 768;
            wc2.CaptureWidth = 1024;
                
        }

        private void button1_Click(object sender, EventArgs e)
        {
            wc1.TimeToCapture_milliseconds = (int)numericUpDown1.Value;
            wc2.TimeToCapture_milliseconds = (int)numericUpDown1.Value;
            wc1.Start(0);
            wc2.Start(0);
            button1.Enabled = false;            
        }

        private void button2_Click(object sender, EventArgs e)
        {
            wc1.Stop();
            wc2.Stop();
            button1.Enabled = true;
        }

        private void button3_Click(object sender, EventArgs e)
        {
            captura1 = true;
            captura2 = true;
            /*pictureBox1.Image.Save("camara1.jpg", ImageFormat.Jpeg);
            pictureBox2.Image.Save("camara2.jpg", ImageFormat.Jpeg);*/
        }

        private void button4_Click(object sender, EventArgs e)
        {
            int ancho = int.Parse(textBox1.Text);
            int alto = int.Parse(textBox2.Text);
            wc1.CaptureHeight = alto;
            wc1.CaptureWidth = ancho;
            wc2.CaptureHeight = alto;
            wc2.CaptureWidth = ancho;
            MessageBox.Show("Establecida nueva resolucion");
        }
    }
}
