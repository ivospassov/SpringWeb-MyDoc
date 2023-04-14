package com.example.webexam.model.enums;

public enum DoctorImageURL {

    MASTURBAKIS_URL("https://st4.depositphotos.com/1325771/39154/i/600/depositphotos_391545206-stock-photo-happy-male-medical-doctor-portrait.jpg"),
    I_HRISTOVA_URL("https://media.istockphoto.com/id/1189304032/photo/doctor-holding-digital-tablet-at-meeting-room.jpg?s=612x612&w=0&k=20&c=RtQn8w_vhzGYbflSa1B5ea9Ji70O8wHpSgGBSh0anUg="),
    BAHCHIEVA_URL("https://s3-eu-west-1.amazonaws.com/intercare-web-public/wysiwyg-uploads%2F1580196666465-doctor.jpg"),
    BALEVA_URL("https://www.yourfreecareertest.com/wp-content/uploads/2018/01/how_to_become_a_doctor.jpg"),
    A_HRISTOVA_URL("https://st2.depositphotos.com/3889193/8015/i/600/depositphotos_80150956-stock-photo-confident-female-doctor-at-office.jpg"),
    SINS_URL("https://www.suddenviral.com/wp-content/uploads/2022/09/Johnny-Sins-4.jpg"),
    JIEBER_URL("https://www.staffcare.com/siteassets/blogs/advice-and-insights/trends-affecting-physician-jobs.jpg"),
    D_GEORGIEVA_URL("https://media.istockphoto.com/id/638647058/photo/we-offer-our-patients-premium-healthcare-here.jpg?s=612x612&w=0&k=20&c=pek5ehwgsZNPemeEh4bObQ1U5DRPEs0WHleosG-daa8="),
    NAYDENOVA_URL("https://thumbs.dreamstime.com/b/confident-female-doctor-posing-her-office-sitting-desk-smiling-camera-health-care-prevention-concept-56352273.jpg"),
    HRISTOV_URL("https://thevideoink.com/wp-content/uploads/2021/11/wysiwyg-uploads_1569586526901-doctor.jpg"),
    AVANTAKIS_URL("https://st4.depositphotos.com/1017986/24362/i/600/depositphotos_243621762-stock-photo-smiling-indian-male-doctor-calling.jpg"),
    STAMENOVA_URL("https://img.freepik.com/free-photo/content-young-female-doctor-posing-camera_1262-18213.jpg"),
    DACHEVA_URL("https://thumbs.dreamstime.com/b/confident-female-doctor-office-desk-sitting-smiling-camera-health-care-prevention-concept-56351853.jpg"),
    R_GEORGIEVA_URL("https://img.freepik.com/free-photo/woman-doctor-wearing-lab-coat-with-stethoscope-isolated_1303-29791.jpg"),
    HARIZANOVA_URL("https://t4.ftcdn.net/jpg/03/17/85/49/360_F_317854905_2idSdvi2ds3yejmk8mhvxYr1OpdVTrSM.jpg"),
    RODRIGUEZ_URL("https://medimap.ca/wp-content/uploads/2022/10/iStock-1193303828.jpg"),
    STILYANOV_URL("https://img.freepik.com/free-photo/handsome-young-male-doctor-with-stethoscope-standing-against-blue-background_662251-343.jpg"),
    U_GEORGIEVA_URL("https://img.freepik.com/free-photo/portrait-smiling-young-woman-doctor-healthcare-medical-worker-pointing-fingers-left-showing-clini_1258-88108.jpg?w=2000"),
    IVANOVA_URL("https://t4.ftcdn.net/jpg/03/17/85/49/360_F_317854905_2idSdvi2ds3yejmk8mhvxYr1OpdVTrSM.jpg"),
    DAZOV_URL("https://t3.ftcdn.net/jpg/02/20/10/92/360_F_220109253_0vS8CGofAGAxDSBqqiq0mou6LXxkekzU.jpg"),
    MINNERUP_URL("https://thumbs.dreamstime.com/b/indian-male-doctor-clipboard-stethoscope-medicine-profession-healthcare-concept-smiling-white-coat-over-grey-138207484.jpg"),
    MILEV_URL("https://media.istockphoto.com/id/1124684854/photo/portrait-of-indian-doctor.jpg?s=612x612&w=0&k=20&c=z07-F84erAbm8Z_sVJhLXdaJBfMFSiJjf_uaHg7Z3sY="),
    IVANOV_URL("https://thumbs.dreamstime.com/b/portrait-happy-young-handsome-indian-man-doctor-smiling-studio-shot-against-white-background-137823661.jpg"),
    STEFANOV_URL("https://thumbs.dreamstime.com/b/portrait-happy-young-handsome-indian-man-doctor-smiling-studio-shot-against-white-background-137823661.jpg"),
    PSOMITAKIS_URL("https://t4.ftcdn.net/jpg/01/85/39/55/360_F_185395570_wUzaCFaFP6Nm7NxWvk5xCMLAdh12nSCZ.jpg"),
    TODOROV_URL("https://t4.ftcdn.net/jpg/03/17/85/49/360_F_317854905_2idSdvi2ds3yejmk8mhvxYr1OpdVTrSM.jpg"),
    KIMBER_URL("https://t4.ftcdn.net/jpg/01/36/18/77/360_F_136187711_qeBMOwkPdTg1dCN8e5TR1AmduXDz60Xn.jpg"),
    MAKLER_URL("https://cdn.stocksnap.io/img-thumbs/960w/male-doctor_KN1OCKC4Y2.jpg"),
    TIMBER_URL("https://thumbs.dreamstime.com/b/outdoor-portrait-male-doctor-wearing-white-lab-coat-smiling-to-camera-35801901.jpg");

    final String imageUrl;

    DoctorImageURL(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
