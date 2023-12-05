package com.codingwithme.meowbottomnavigationbar

import android.content.Intent
import android.graphics.Typeface
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ListView
import android.widget.TextView
import android.widget.Toolbar
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import java.util.Random
import android.content.Context
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


class DrugdrugFragment : Fragment() {
    private lateinit var adapter: MyAdapter
    private lateinit var items: List<Item>
    private var buttonCount = 0
    private val createdButtons = mutableListOf<Button>()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_drugdrug, container, false)
        val listView = view.findViewById<ListView>(R.id.listView)
        val searchView = view.findViewById<SearchView>(R.id.searchView)


        items = listOf(
            Item("Abemaciclib"), Item("Abiraterone"), Item("Acarbose"),Item("Acebutolol"), Item("Aceclofenac"), Item("Acemetacin"), Item("Acenocoumarol"), Item("Acepromazine"), Item("Aceprometazine"),Item("Acetaminophen"), Item("Acetazolamide"),Item("Acetohexamide"), Item("Acetophenazine"),Item("Acetyl sulfisoxazole"), Item("Acetylcholine"),Item("Acetyldigitoxin"), Item("Acetylsalicylic acid"), Item("Acipimox"),Item("Acitretin"), Item("Aclidinium"), Item("Acrivastine"), Item("Activated charcoal"), Item("Acyclovir"), Item("Adapalene"), Item("Adefovir Dipivoxil"), Item("Adenine"), Item("Adenosine"), Item("Adinazolam"), Item("Afatinib"), Item("Agomelatine"), Item("Ajmaline"), Item("Alatrofloxacin"), Item("Albendazole"), Item("Alcaftadine"), Item("Alclofenac"), Item("Alclometasone"), Item("Alectinib"), Item("Alendronic acid"), Item("Alfacalcidol"), Item("Alfentanil"), Item("Alfuzosin"), Item("Alimemazine"), Item("Aliskiren"), Item("Alitretinoin"), Item("Alizapride"), Item("Allopurinol"), Item("Allylestrenol"), Item("Almasilate"), Item("Almotriptan"), Item("Alogliptin"), Item("Aloglutamol"), Item("Alosetron"), Item("Alprazolam"), Item("Alprenolol"), Item("Alprostadil"), Item("Altretamine"), Item("Aluminium"), Item("Aluminum hydroxide"), Item("Aluminum oxide"), Item("Aluminum sulfate"), Item("Alvimopan"), Item("Amantadine"), Item("Ambenonium"), Item("Ambrisentan"), Item("Ambroxol"), Item("Amcinonide"), Item("Amifostine"), Item("Amikacin"), Item("Amiloride"), Item("Aminocaproic Acid"), Item("Aminoglutethimide"), Item("Aminohippuric acid"), Item("Aminolevulinic acid"), Item("Aminophenazone"), Item("Aminophylline"), Item("Aminosalicylic Acid"), Item("Amiodarone"), Item("Amisulpride"), Item("Amitriptyline"), Item("Amitriptylinoxide"), Item("Amlodipine"), Item("Ammonium chloride"), Item("Amobarbital"), Item("Amodiaquine"), Item("Amorolfine"), Item("Amoxapine"), Item("Amoxicillin"), Item("Amphetamine"), Item("Amphotericin B"), Item("Ampicillin"), Item("Amprenavir"), Item("Amrinone"), Item("Amrubicin"), Item("Amsacrine"), Item("Amyl Nitrite"), Item("Anagrelide"), Item("Anastrozole"), Item("Angiotensin II"), Item("Anidulafungin"), Item("Anisotropine Methylbromide"), Item("Antazoline"), Item("Antipyrine"), Item("Antrafenine"), Item("Apalutamide"), Item("Apixaban"), Item("Apomorphine"), Item("Apraclonidine"), Item("Apremilast"), Item("Aprepitant"), Item("Aprindine"), Item("Aprobarbital"), Item("Aranidipine"), Item("Arbekacin"), Item("Arbutamine"), Item("Arformoterol"), Item("Argatroban"), Item("Aripiprazole"), Item("Armodafinil"), Item("Arsenic trioxide"), Item("Artemether"), Item("Artemotil"), Item("Artenimol"), Item("Artesunate"), Item("Articaine"), Item("Asenapine"), Item("Astemizole"), Item("Asunaprevir"), Item("Atazanavir"), Item("Atenolol"), Item("Atomoxetine"), Item("Atorvastatin"), Item("Atosiban"), Item("Atovaquone"), Item("Atracurium besylate"), Item("Atropine"), Item("Avanafil"), Item("Avatrombopag"), Item("Avibactam"), Item("Axitinib"), Item("Azacitidine"), Item("Azatadine"), Item("Azathioprine"), Item("Azelaic Acid"), Item("Azelastine"), Item("Azelnidipine"), Item("Azidocillin"), Item("Azilsartan medoxomil"), Item("Azithromycin"), Item("Azlocillin"), Item("Aztreonam"), Item("Bacampicillin"), Item("Baclofen"), Item("Balsalazide"), Item("Bambuterol"), Item("Baricitinib"), Item("Barnidipine"), Item("Bazedoxifene"), Item("Beclomethasone dipropionate"), Item("Bedaquiline"), Item("Belinostat"), Item("Benazepril"), Item("Bendamustine"), Item("Bendroflumethiazide"), Item("Benfotiamine"), Item("Benidipine"), Item("Benperidol"), Item("Benzatropine"), Item("Benznidazole"), Item("Benzocaine"), Item("Benzoctamine"), Item("Benzoic Acid"), Item("Benzophenone"), Item("Benzoyl peroxide"), Item("Benzphetamine"), Item("Benzthiazide"), Item("Benzydamine"), Item("Benzyl alcohol"), Item("Benzylpenicillin"), Item("Benzylpenicilloyl Polylysine"), Item("Bepotastine"), Item("Bepridil"), Item("Besifloxacin"), Item("Betahistine"), Item("Betamethasone"), Item("Betaxolol"), Item("Bethanechol"), Item("Bethanidine"), Item("Betrixaban"), Item("Bevantolol"), Item("Bexarotene"), Item("Bezafibrate"), Item("Bicalutamide"), Item("Bictegravir"), Item("Bifonazole"), Item("Bilastine"), Item("Bimatoprost"), Item("Biperiden"), Item("Bisacodyl"), Item("Bismuth Subcitrate"), Item("Bismuth subnitrate"), Item("Bismuth Subsalicylate"), Item("Bisoprolol"), Item("Bivalirudin"), Item("Bleomycin"), Item("Blonanserin"), Item("Boceprevir"), Item("Bopindolol"), Item("Bortezomib"), Item("Bosentan"), Item("Bosutinib"), Item("Bretylium"), Item("Brexpiprazole"), Item("Brigatinib"), Item("Brimonidine"), Item("Brinzolamide"), Item("Brivaracetam"), Item("Bromazepam"), Item("Bromfenac"), Item("Bromocriptine"), Item("Bromodiphenhydramine"),
            Item("Bromopride"), Item("Bromotheophylline"), Item("Bromperidol"), Item("Brompheniramine"), Item("Brotizolam"), Item("Buclizine"), Item("Budesonide"), Item("Bufexamac"), Item("Bumetanide"), Item("Bupivacaine"), Item("Bupranolol"), Item("Buprenorphine"), Item("Bupropion"), Item("Buspirone"), Item("Busulfan"), Item("Butabarbital"), Item("Butalbital"), Item("Butamben"), Item("Butenafine"), Item("Butethal"), Item("Butoconazole"), Item("Butorphanol"), Item("Butriptyline"), Item("Butylscopolamine"), Item("Cabazitaxel"), Item("Cabergoline"), Item("Cabozantinib"), Item("Caffeine"), Item("Calcidiol"), Item("Calcipotriol"), Item("Calcitriol"), Item("Calcium Acetate"), Item("Calcium carbimide"), Item("Calcium Carbonate"), Item("Calcium Chloride"), Item("Calcium Citrate"), Item("Calcium glubionate"), Item("Calcium gluconate"), Item("Calcium lactate"), Item("Calcium levulinate"), Item("Calcium Phosphate"), Item("Camazepam"), Item("Canagliflozin"), Item("Candesartan cilexetil"), Item("Cangrelor"), Item("Cannabidiol"), Item("Canrenoic acid"), Item("Capecitabine"), Item("Capreomycin"), Item("Capsaicin"), Item("Captodiame"), Item("Captopril"), Item("Carbachol"), Item("Carbamazepine"), Item("Carbenicillin"), Item("Carbenicillin indanyl"), Item("Carbetocin"), Item("Carbidopa"), Item("Carbimazole"), Item("Carbinoxamine"), Item("Carboplatin"), Item("Carboprost Tromethamine"), Item("Carfilzomib"), Item("Cariprazine"), Item("Carisoprodol"), Item("Carmustine"), Item("Carprofen"), Item("Carteolol"), Item("Carvedilol"), Item("Caspofungin"), Item("Cefacetrile"), Item("Cefaclor"), Item("Cefadroxil"), Item("Cefalotin"), Item("Cefamandole"), Item("Cefapirin"), Item("Cefazolin"), Item("Cefdinir"), Item("Cefditoren"), Item("Cefepime"), Item("Cefixime"), Item("Cefmenoxime"), Item("Cefmetazole"), Item("Cefminox"), Item("Cefonicid"), Item("Cefoperazone"), Item("Ceforanide"), Item("Cefotaxime"), Item("Cefotetan"), Item("Cefotiam"), Item("Cefoxitin"), Item("Cefpiramide"), Item("Cefpirome"), Item("Cefpodoxime"), Item("Cefprozil"), Item("Cefradine"), Item("Ceftaroline fosamil"), Item("Ceftazidime"), Item("Ceftibuten"), Item("Ceftizoxime"), Item("Ceftobiprole"), Item("Ceftriaxone"), Item("Cefuroxime"), Item("Celecoxib"), Item("Celiprolol"), Item("Cephalexin"), Item("Cephaloglycin"), Item("Cephaloridine"), Item("Ceritinib"), Item("Cerivastatin"), Item("Cerulenin"),
            Item("Cetirizine"), Item("Cevimeline"), Item("Chenodeoxycholic acid"), Item("Chloral hydrate"), Item("Chlorambucil"), Item("Chloramphenicol"), Item("Chlorcyclizine"), Item("Chlordiazepoxide"), Item("Chlormezanone"), Item("Chloroprocaine"), Item("Chloropyramine"), Item("Chloroquine"), Item("Chlorothiazide"), Item("Chloroxine"), Item("Chloroxylenol"), Item("Chlorphenamine"), Item("Chlorphenesin"), Item("Chlorpromazine"), Item("Chlorpropamide"), Item("Chlorprothixene"), Item("Chlortetracycline"), Item("Chlorthalidone"), Item("Chlorzoxazone"), Item("Cholecalciferol"), Item("Cholesterol"), Item("Cholic Acid"), Item("Choline"), Item("Choline C 11"), Item("Choline magnesium trisalicylate"), Item("Choline salicylate"), Item("Chromium picolinate"), Item("Ciclesonide"), Item("Ciclopirox"), Item("Cidofovir"), Item("Cilastatin"), Item("Cilazapril"), Item("Cilnidipine"), Item("Cilostazol"), Item("Cimetidine"), Item("Cinacalcet"), Item("Cinchocaine"), Item("Cinitapride"), Item("Cinnarizine"), Item("Cinolazepam"), Item("Cinoxacin"), Item("Ciprofibrate"), Item("Ciprofloxacin"), Item("Cisapride"), Item("Cisatracurium"), Item("Cisplatin"), Item("Citalopram"), Item("Citric Acid"), Item("Cladribine"), Item("Clarithromycin"), Item("Clavulanate"), Item("Clemastine"), Item("Clenbuterol"), Item("Clevidipine"), Item("Clidinium"), Item("Clindamycin"), Item("Clobazam"), Item("Clobetasol"), Item("Clobetasol propionate"), Item("Clobetasone"), Item("Clocortolone"), Item("Clodronic Acid"), Item("Clofarabine"), Item("Clofazimine"), Item("Clofedanol"), Item("Clofibrate"), Item("Clomifene"), Item("Clomipramine"), Item("Clomocycline"), Item("Clonazepam"), Item("Clonidine"), Item("Clonixin"), Item("Clopidogrel"), Item("Clorazepate"), Item("Clotiazepam"), Item("Clotrimazole"), Item("Cloxacillin"), Item("Cloxazolam"), Item("Clozapine"), Item("Cobicistat"), Item("Cobimetinib"), Item("Cocaine"), Item("Codeine"), Item("Colchicine"), Item("Colistimethate"), Item("Conivaptan"), Item("Copanlisib"), Item("Copper"), Item("Corticorelin ovine triflutate"), Item("Cortisone acetate"), Item("Crisaborole"), Item("Crizotinib"), Item("Curcumin"), Item("Cyamemazine"), Item("Cyanocobalamin"), Item("Cyclacillin"), Item("Cyclandelate"), Item("Cyclizine"), Item("Cyclobenzaprine"), Item("Cyclopenthiazide"), Item("Cyclopentolate"), Item("Cyclophosphamide"), Item("Cycloserine"), Item("Cyclosporine"), Item("Cyclothiazide"), Item("Cyproheptadine"), Item("Cyproterone acetate"), Item("Cysteamine"), Item("Cytarabine"), Item("Dabigatran etexilate"), Item("Dabrafenib"), Item("Dacarbazine"), Item("Daclatasvir"), Item("Dactinomycin"), Item("Dalfampridine"), Item("Dalfopristin"), Item("Danazol"), Item("Dantrolene"), Item("Dapagliflozin"), Item("Dapiprazole"), Item("Dapsone"), Item("Daptomycin"), Item("Darifenacin"), Item("Darunavir"), Item("Dasabuvir"), Item("Dasatinib"), Item("Daunorubicin"), Item("Debrisoquin"), Item("Decamethonium"), Item("Decitabine"), Item("Deferasirox"), Item("Deferiprone"), Item("Deferoxamine"), Item("Deflazacort"), Item("Degarelix"), Item("Delafloxacin"), Item("Delamanid"), Item("Delavirdine"), Item("Delorazepam"), Item("Demecarium"), Item("Demeclocycline"), Item("Demegestone"), Item("Deoxycholic Acid"), Item("Deserpidine"), Item("Desflurane"), Item("Desipramine"), Item("Deslanoside"), Item("Desloratadine"), Item("Desmopressin"), Item("Desogestrel"), Item("Desonide"), Item("Desoximetasone"), Item("Desoxycorticosterone acetate"), Item("Desvenlafaxine"), Item("Deutetrabenazine"), Item("Dexamethasone"), Item("Dexbrompheniramine"), Item("Dexchlorpheniramine maleate"), Item("Dexfenfluramine"), Item("Dexibuprofen"), Item("Dexketoprofen"), Item("Dexlansoprazole"), Item("Dexmedetomidine"), Item("Dexmethylphenidate"), Item("Dexrazoxane"), Item("Dextroamphetamine"), Item("Dextromethorphan"), Item("Dextropropoxyphene"), Item("Dextrothyroxine"), Item("Dezocine"), Item("Diacerein"), Item("Diatrizoate"), Item("Diazepam"), Item("Diazoxide"), Item("Dichloralphenazone"), Item("Diclofenac"), Item("Diclofenamide"), Item("Dicloxacillin"), Item("Dicoumarol"), Item("Dicyclomine"), Item("Didanosine"), Item("Dienestrol"), Item("Dienogest"), Item("Diethylcarbamazine"), Item("Diethylpropion"), Item("Diethylstilbestrol"), Item("Diethyltoluamide"), Item("Difenoxin"), Item("Diflorasone"), Item("Diflunisal"), Item("Difluocortolone"), Item("Difluprednate"), Item("Digitoxin"), Item("Digoxin"), Item("Dihydralazine"), Item("Dihydro-alpha-ergocryptine"),
            Item("Dihydrocodeine"), Item("Dihydroergocornine"), Item("Dihydroergocristine"), Item("Dihydroergotamine"), Item("Dihydrotachysterol"), Item("Diltiazem"), Item("Dimenhydrinate"), Item("Dimercaprol"), Item("Dimetacrine"), Item("Dimethyl fumarate"), Item("Dimethyl sulfoxide"), Item("Dimetindene"), Item("Dimetotiazine"), Item("Dinoprost"), Item("Dinoprost Tromethamine"), Item("Dinoprostone"), Item("Diphenhydramine"), Item("Diphenoxylate"), Item("Diphenylpyraline"), Item("Dipivefrin"), Item("Dipotassium phosphate"), Item("Dipyridamole"), Item("Dirithromycin"), Item("Disopyramide"), Item("Disulfiram"), Item("Ditazole"), Item("DL-Methylephedrine"), Item("Dobutamine"), Item("Docetaxel"), Item("Doconexent"), Item("Dofetilide"), Item("Dolasetron"), Item("Dolutegravir"), Item("Domperidone"), Item("Donepezil"), Item("Dopamine"), Item("Dopexamine"), Item("Doripenem"), Item("Dorzolamide"), Item("Dosulepin"), Item("Dotatate gallium Ga-68"), Item("Doxacurium chloride"), Item("Doxapram"), Item("Doxazosin"), Item("Doxepin"), Item("Doxercalciferol"), Item("Doxofylline"), Item("Doxorubicin"), Item("Doxycycline"), Item("Doxylamine"), Item("Dronabinol"), Item("Dronedarone"), Item("Droperidol"), Item("Drospirenone"), Item("Droxidopa"), Item("Duloxetine"), Item("Dutasteride"), Item("Dyclonine"), Item("Dydrogesterone"), Item("Dyphylline"), Item("Ebastine"), Item("Ecabet"), Item("Echothiophate"), Item("Econazole"), Item("Edetic Acid"), Item("Edoxaban"), Item("Edrophonium"), Item("Efavirenz"), Item("Efinaconazole"), Item("Eflornithine"), Item("Efonidipine"), Item("Elbasvir"), Item("Eletriptan"), Item("Eliglustat"), Item("Eltrombopag"), Item("Eluxadoline"), Item("Elvitegravir"), Item("Emedastine"), Item("Empagliflozin"), Item("Emtricitabine"), Item("Enalapril"), Item("Enalaprilat"), Item("Enasidenib"), Item("Encainide"), Item("Enflurane"), Item("Enoxacin"), Item("Entacapone"), Item("Enzalutamide"), Item("Eperisone"), Item("Ephedrine"), Item("Epinastine"), Item("Epinephrine"), Item("Epirizole"), Item("Epirubicin"), Item("Eplerenone"), Item("Epoprostenol"), Item("Eprosartan"), Item("Equilin"), Item("Ergocalciferol"), Item("Ergonovine"), Item("Ergotamine"), Item("Eribulin"), Item("Erlotinib"), Item("Ertapenem"), Item("Ertugliflozin"), Item("Erythrityl Tetranitrate"), Item("Erythromycin"), Item("Escitalopram"), Item("Eslicarbazepine acetate"), Item("Esmolol"), Item("Esomeprazole"), Item("Estazolam"), Item("Estradiol"), Item("Estradiol acetate"), Item("Estradiol benzoate"), Item("Estradiol cypionate"), Item("Estradiol dienanthate"), Item("Estradiol valerate"), Item("Estramustine"), Item("Estriol"), Item("Estrone"), Item("Estrone sulfate"), Item("Eszopiclone"), Item("Etacrynic acid"), Item("Etafedrine"), Item("Ethambutol"), Item("Ethanol"), Item("Ethanolamine Oleate"), Item("Ethchlorvynol"), Item("Ethinyl Estradiol"), Item("Ethionamide"), Item("Ethopropazine"), Item("Ethosuximide"), Item("Ethotoin"), Item("Ethyl chloride"), Item("Ethyl loflazepate"), Item("Ethylmorphine"), Item("Ethynodiol diacetate"), Item("Etidocaine"), Item("Etidronic acid"), Item("Etizolam"), Item("Etodolac"), Item("Etofenamate"), Item("Etofibrate"), Item("Etomidate"), Item("Etonogestrel"), Item("Etoposide"), Item("Etoricoxib"), Item("Etravirine"), Item("Everolimus"), Item("Exemestane"), Item("Ezetimibe"), Item("Ezogabine"), Item("Famciclovir"), Item("Famotidine"), Item("Febuxostat"), Item("Felbamate"), Item("Felodipine"), Item("Felypressin"), Item("Fenbufen"), Item("Fencamfamine"), Item("Fenfluramine"), Item("Fenofibrate"), Item("Fenofibric acid"), Item("Fenoldopam"), Item("Fenoprofen"), Item("Fenoterol"), Item("Fentanyl"), Item("Ferric cation"), Item("Ferric sulfate"), Item("Ferrous bisglycinate"), Item("Ferrous sulfate"), Item("Fesoterodine"), Item("Fexofenadine"), Item("Fidaxomicin"), Item("Fimasartan"), Item("Finasteride"), Item("Fingolimod"), Item("Flavoxate"), Item("Flecainide"), Item("Fleroxacin"), Item("Flibanserin"), Item("Floctafenine"), Item("Floxuridine"), Item("Flubendazole"), Item("Fluciclovine (18F)"), Item("Flucloxacillin"), Item("Fluconazole"), Item("Flucytosine"), Item("Fludarabine"), Item("Fludiazepam"), Item("Fludrocortisone"), Item("Fluindione"), Item("Flumazenil"), Item("Flumethasone"), Item("Flunarizine"), Item("Flunisolide"), Item("Flunitrazepam"), Item("Fluocinolone Acetonide"), Item("Fluocinonide"), Item("Fluocortolone"), Item("Fluorometholone"),
            Item("Fluorouracil"), Item("Fluoxetine"), Item("Fluoxymesterone"), Item("Flupentixol"), Item("Fluphenazine"), Item("Fluprednisolone"), Item("Flurandrenolide"), Item("Flurazepam"), Item("Flurbiprofen"), Item("Fluspirilene"), Item("Flutamide"), Item("Fluticasone"), Item("Fluticasone furoate"), Item("Fluticasone propionate"), Item("Fluvastatin"), Item("Fluvoxamine"), Item("Folic Acid"), Item("Fomepizole"), Item("Fondaparinux sodium"), Item("Formaldehyde"), Item("Formestane"), Item("Formoterol"), Item("Fosamprenavir"), Item("Fosaprepitant"), Item("Foscarnet"), Item("Fosinopril"), Item("Fosnetupitant"), Item("Fosphenytoin"), Item("Fospropofol"), Item("Fostamatinib"), Item("Framycetin"), Item("Frovatriptan"), Item("Fulvestrant"), Item("Furazolidone"), Item("Furosemide"), Item("Fusafungine"), Item("Fusidic Acid"), Item("Gabapentin"), Item("Gabapentin Enacarbil"), Item("Gadobenic acid"), Item("Gadoxetic acid"), Item("Galantamine"), Item("Gallamine Triethiodide"), Item("Gallium nitrate"), Item("Gamma Hydroxybutyric Acid"), Item("Ganciclovir"), Item("Gatifloxacin"), Item("Gefitinib"), Item("Gemcitabine"), Item("Gemeprost"), Item("Gemfibrozil"), Item("Gemifloxacin"), Item("Gentamicin"), Item("Gestodene"), Item("Gestrinone"), Item("Gimeracil"), Item("Glecaprevir"), Item("Gliclazide"), Item("Glimepiride"), Item("Glipizide"), Item("Gliquidone"), Item("Gluconic Acid"), Item("Glucosamine"), Item("Glutamic Acid"), Item("Glutethimide"), Item("Glyburide"), Item("Glycerin"), Item("Glycerol Phenylbutyrate"), Item("Glycine"), Item("Glycopyrronium"), Item("Goserelin"), Item("Gramicidin D"), Item("Granisetron"), Item("Grazoprevir"), Item("Grepafloxacin"), Item("Griseofulvin"), Item("Guanabenz"), Item("Guanadrel"), Item("Guanethidine"), Item("Guanfacine"), Item("Halazepam"), Item("Halcinonide"), Item("Halofantrine"), Item("Haloperidol"), Item("Haloprogin"), Item("Halothane"), Item("Heptabarbital"), Item("Heroin"), Item("Hexafluronium"), Item("Hexaminolevulinate"), Item("Hexetidine"), Item("Hexobarbital"), Item("Hexoprenaline"), Item("Hexylresorcinol"), Item("Histamine"), Item("Histrelin"), Item("Homatropine"), Item("Huperzine A"), Item("Hydralazine"), Item("Hydrochlorothiazide"), Item("Hydrocodone"), Item("Hydrocortamate"), Item("Hydrocortisone"), Item("Hydroflumethiazide"), Item("Hydromorphone"), Item("Hydrotalcite"), Item("Hydroxyamphetamine"), Item("Hydroxychloroquine"), Item("Hydroxyprogesterone caproate"), Item("Hydroxyurea"), Item("Hydroxyzine"), Item("Hyoscyamine"), Item("Ibandronate"), Item("Ibrutinib"), Item("Ibudilast"), Item("Ibuprofen"), Item("Ibutilide"), Item("Icatibant"), Item("Icosapent"), Item("Icosapent ethyl"), Item("Icotinib"), Item("Idarubicin"), Item("Idelalisib"), Item("Ifenprodil"), Item("Ifosfamide"), Item("Iloperidone"), Item("Iloprost"), Item("Imatinib"), Item("Imidafenacin"), Item("Imipenem"), Item("Imipramine"), Item("Imiquimod"), Item("Indacaterol"), Item("Indapamide"), Item("Indinavir"), Item("Indomethacin"), Item("Iobenguane"),
            Item("Iodide I-131"), Item("Iodipamide"), Item("Iodixanol"), Item("Ioflupane I-123"), Item("Iohexol"), Item("Iopamidol"), Item("Iopromide"), Item("Ioversol"), Item("Ioxilan"), Item("Ipratropium bromide"), Item("Irbesartan"), Item("Irinotecan"), Item("Iron"), Item("Iron saccharate"), Item("Isavuconazole"), Item("Isavuconazonium"), Item("Isocarboxazid"), Item("Isoconazole"), Item("Isoetarine"), Item("Isoflurane"), Item("Isoflurophate"), Item("Isometheptene"), Item("Isoniazid"), Item("Isoprenaline"), Item("Isosorbide"), Item("Isosorbide Dinitrate"), Item("Isosorbide Mononitrate"), Item("Isothipendyl"), Item("Isotretinoin"), Item("Isoxsuprine"), Item("Isradipine"), Item("Itraconazole"), Item("Ivabradine"), Item("Ivacaftor"), Item("Ivermectin"), Item("Ixabepilone"), Item("Ixazomib"), Item("Josamycin"), Item("Kanamycin"), Item("Kaolin"), Item("Ketamine"), Item("Ketazolam"), Item("Ketobemidone"), Item("Ketoconazole"), Item("Ketoprofen"), Item("Ketorolac"), Item("Ketotifen"), Item("Labetalol"), Item("Lacidipine"), Item("Lacosamide"), Item("Lactic Acid"), Item("Lactulose"), Item("Lamivudine"), Item("Lamotrigine"), Item("Lanreotide"), Item("Lansoprazole"), Item("Lanthanum carbonate"), Item("Lapatinib"), Item("Lasofoxifene"), Item("Latamoxef"), Item("Latanoprost"), Item("Latanoprostene Bunod"), Item("L-Carnitine"), Item("Ledipasvir"), Item("Leflunomide"), Item("Lenalidomide"), Item("Lenvatinib"), Item("Lercanidipine"), Item("Lesinurad"), Item("Letermovir"), Item("Letrozole"), Item("Leucovorin"), Item("Levallorphan"), Item("Levamisole"), Item("Levetiracetam"), Item("Levobetaxolol"), Item("Levobunolol"), Item("Levobupivacaine"), Item("Levocabastine"), Item("Levocetirizine"), Item("Levodopa"), Item("Levofloxacin"), Item("Levoleucovorin"), Item("Levomefolic acid"), Item("Levomenol"), Item("Levomethadyl Acetate"), Item("Levomilnacipran"), Item("Levonorgestrel"), Item("Levorphanol"), Item("Levosalbutamol"), Item("Levosimendan"), Item("Levothyroxine"), Item("L-Glutamine"), Item("Lidocaine"), Item("Limaprost"), Item("Linagliptin"), Item("Lincomycin"), Item("Linezolid"), Item("Liothyronine"), Item("Liotrix"), Item("Lipoic Acid"), Item("Lisdexamfetamine"), Item("Lisinopril"), Item("Lisuride"), Item("Lixisenatide"), Item("Lobeglitazone"), Item("Lodoxamide"), Item("Lofexidine"), Item("Lomefloxacin"), Item("Lomitapide"), Item("Lomustine"), Item("Loperamide"), Item("Lopinavir"), Item("Loracarbef"), Item("Loratadine"), Item("Lorazepam"), Item("Lorcaserin"), Item("Lormetazepam"), Item("Lornoxicam"), Item("Lorpiprazole"), Item("Losartan"), Item("Loteprednol"), Item("Lovastatin"), Item("Loxapine"), Item("Loxoprofen"), Item("L-Phenylalanine"), Item("L-Tryptophan"), Item("Lubiprostone"), Item("Lucanthone"), Item("Luliconazole"), Item("Lumacaftor"), Item("Lumefantrine"), Item("Lumiracoxib"), Item("Lurasidone"), Item("Lutetium Lu 177 dotatate"), Item("Lycopene"), Item("Lymecycline"), Item("Lynestrenol"), Item("Macimorelin"), Item("Macitentan"), Item("Mafenide"), Item("Magaldrate"), Item("Magnesium carbonate"), Item("Magnesium hydroxide"), Item("Magnesium oxide"), Item("Magnesium salicylate"), Item("Magnesium silicate"), Item("Magnesium sulfate"), Item("Magnesium Trisilicate"), Item("Malathion"), Item("Manidipine"), Item("Mannitol"), Item("Maprotiline"), Item("Maraviroc"), Item("Masoprocol"),
            Item("Maxacalcitol"), Item("Mebendazole"), Item("Mebeverine"), Item("Mebutamate"), Item("Mecamylamine"), Item("Mechlorethamine"), Item("Meclizine"), Item("Meclofenamic acid"), Item("Medrogestone"), Item("Medroxyprogesterone acetate"), Item("Medrysone"), Item("Mefenamic acid"), Item("Mefloquine"), Item("Megestrol acetate"), Item("Melatonin"), Item("Meloxicam"), Item("Melperone"), Item("Melphalan"), Item("Memantine"), Item("Menadione"), Item("Menthol"), Item("Mephenesin"), Item("Mephentermine"), Item("Mephenytoin"), Item("Mepivacaine"), Item("Meprednisone"), Item("Meprobamate"), Item("Mepyramine"), Item("Mequinol"), Item("Mequitazine"), Item("Mercaptopurine"), Item("Meropenem"), Item("Mersalyl"), Item("Mesalazine"), Item("Mesoridazine"), Item("Mestranol"), Item("Metamizole"), Item("Metaraminol"), Item("Metaxalone"), Item("Metformin"), Item("Methacholine"), Item("Methacycline"), Item("Methadone"), Item("Methadyl Acetate"), Item("Methamphetamine"), Item("Methantheline"), Item("Methazolamide"), Item("Methdilazine"), Item("Methenamine"), Item("Methimazole"), Item("Methionine"), Item("Methocarbamol"), Item("Methohexital"), Item("Methotrexate"), Item("Methotrimeprazine"), Item("Methoxamine"), Item("Methoxsalen"), Item("Methoxyflurane"), Item("Methscopolamine"), Item("Methsuximide"), Item("Methyclothiazide"), Item("Methyl aminolevulinate"), Item("Methyl salicylate"), Item("Methyldopa"), Item("Methylene blue"), Item("Methylergometrine"), Item("Methylnaltrexone"), Item("Methylphenidate"), Item("Methylphenobarbital"), Item("Methylprednisolone"), Item("Methylscopolamine bromide"), Item("Methyltestosterone"), Item("Methyprylon"), Item("Methysergide"), Item("Meticillin"), Item("Metipranolol"), Item("Metixene"), Item("Metoclopramide"), Item("Metocurine"), Item("Metocurine Iodide"), Item("Metolazone"), Item("Metoprolol"), Item("Metronidazole"), Item("Metyrapone"), Item("Metyrosine"), Item("Mexiletine"), Item("Mezlocillin"), Item("Mianserin"), Item("Micafungin"), Item("Miconazole"), Item("Midazolam"), Item("Midecamycin"), Item("Midodrine"), Item("Midostaurin"), Item("Mifamurtide"), Item("Mifepristone"), Item("Miglitol"), Item("Miglustat"), Item("Milnacipran"), Item("Milrinone"), Item("Miltefosine"), Item("Minaprine"), Item("Minocycline"), Item("Minoxidil"), Item("Mipomersen"), Item("Mirabegron"), Item("Mirtazapine"), Item("Misoprostol"), Item("Mitiglinide"), Item("Mitomycin"), Item("Mitotane"), Item("Mitoxantrone"), Item("Mivacurium"), Item("Moclobemide"), Item("Modafinil"), Item("Moexipril"), Item("Molindone"), Item("Molsidomine"), Item("Mometasone"), Item("Montelukast"), Item("Moricizine"), Item("Morniflumate"), Item("Morphine"), Item("Moxidectin"), Item("Moxifloxacin"), Item("Moxonidine"), Item("Mupirocin"), Item("Mycophenolate mofetil"), Item("Mycophenolic acid"), Item("Nabilone"), Item("Nabiximols"), Item("Nabumetone"), Item("Nadolol"), Item("Nafamostat"), Item("Nafcillin"), Item("Naftifine"), Item("Nalbuphine"), Item("Naldemedine"),
            Item("Nalidixic Acid"), Item("Nalmefene"), Item("Nalorphine"), Item("Naloxegol"), Item("Naloxone"), Item("Naltrexone"), Item("Nandrolone decanoate"), Item("Naphazoline"), Item("Naproxen"), Item("Naratriptan"), Item("Natamycin"), Item("Nateglinide"), Item("Nebivolol"), Item("Nedaplatin"), Item("Nefazodone"), Item("Nelarabine"), Item("Nelfinavir"), Item("Neostigmine"), Item("Nepafenac"), Item("Neratinib"), Item("Netilmicin"), Item("Netupitant"), Item("Nevirapine"), Item("Niacin"), Item("Nicardipine"), Item("Nicergoline"), Item("Niclosamide"), Item("Nicorandil"), Item("Nicotinamide"), Item("Nicotine"), Item("Nifedipine"), Item("Niflumic Acid"), Item("Nilotinib"), Item("Nilutamide"), Item("Nilvadipine"), Item("Nimesulide"), Item("Nimodipine"), Item("Nintedanib"), Item("Niraparib"), Item("Nisoldipine"), Item("Nitrazepam"), Item("Nitrendipine"), Item("Nitric Oxide"), Item("Nitrofural"), Item("Nitrofurantoin"), Item("Nitroglycerin"), Item("Nitroprusside"), Item("Nitrous acid"), Item("Nitrous oxide"), Item("Nitroxoline"), Item("Nizatidine"), Item("Nomegestrol"), Item("Nomegestrol acetate"), Item("Nonoxynol-9"), Item("Nordazepam"), Item("Norelgestromin"), Item("Norepinephrine"), Item("Norethisterone"), Item("Norethynodrel"), Item("Norfloxacin"), Item("Norflurane"), Item("Norgestimate"), Item("Norgestrel"), Item("Normethadone"), Item("Nortriptyline"), Item("Novobiocin"), Item("Nylidrin"), Item("Nystatin"), Item("Obeticholic acid"), Item("Octreotide"), Item("Ofloxacin"), Item("Olanzapine"), Item("Olaparib"), Item("Olmesartan"), Item("Olodaterol"), Item("Olopatadine"), Item("Olsalazine"), Item("Omacetaxine mepesuccinate"), Item("Ombitasvir"), Item("Omeprazole"), Item("Ondansetron"), Item("Opicapone"), Item("Orciprenaline"), Item("Oritavancin"), Item("Orlistat"), Item("Orphenadrine"), Item("Oseltamivir"), Item("Osimertinib"), Item("Ospemifene"), Item("Ouabain"), Item("Oxacillin"), Item("Oxaliplatin"), Item("Oxamniquine"), Item("Oxandrolone"), Item("Oxaprozin"), Item("Oxazepam"), Item("Oxcarbazepine"), Item("Oxetacaine"), Item("Oxiconazole"), Item("Oxitriptan"), Item("Oxprenolol"), Item("Oxtriphylline"), Item("Oxybuprocaine"), Item("Oxybutynin"), Item("Oxycodone"), Item("Oxymetazoline"), Item("Oxymetholone"), Item("Oxymorphone"), Item("Oxyphenbutazone"), Item("Oxyphenonium"), Item("Oxytetracycline"), Item("Ozenoxacin"), Item("Paclitaxel"), Item("Palbociclib"), Item("Paliperidone"), Item("Palmitic Acid"), Item("Palonosetron"), Item("Pamidronate"), Item("Pancuronium"), Item("Panobinostat"), Item("Pantoprazole"), Item("Papaverine"), Item("Paraldehyde"), Item("Paramethadione"), Item("Paramethasone"), Item("Parecoxib"), Item("Pargyline"), Item("Paricalcitol"), Item("Paritaprevir"), Item("Paromomycin"), Item("Paroxetine"), Item("Parthenolide"), Item("Pasireotide"), Item("Patent Blue"), Item("Pazopanib"), Item("Pefloxacin"), Item("Pemetrexed"), Item("Pemirolast"), Item("Penbutolol"), Item("Penicillamine"), Item("Pentaerythritol Tetranitrate"), Item("Pentamidine"), Item("Pentazocine"), Item("Pentetic acid"), Item("Pentobarbital"), Item("Pentolinium"), Item("Pentosan Polysulfate"), Item("Pentostatin"), Item("Pentoxifylline"), Item("Pentoxyverine"), Item("Perampanel"), Item("Perazine"), Item("Perflubutane"), Item("Perflutren"), Item("Pergolide"), Item("Perhexiline"), Item("Perindopril"), Item("Permethrin"), Item("Perospirone"), Item("Perphenazine"), Item("Pethidine"), Item("Phenacemide"), Item("Phenazopyridine"), Item("Phendimetrazine"), Item("Phenelzine"), Item("Phenformin"), Item("Phenindamine"), Item("Phenindione"), Item("Pheniramine"), Item("Phenmetrazine"),
            Item("Phenobarbital"), Item("Phenoxybenzamine"), Item("Phenoxyethanol"), Item("Phenoxymethylpenicillin"), Item("Phenprocoumon"), Item("Phensuximide"), Item("Phentermine"), Item("Phentolamine"), Item("Phenyl aminosalicylate"), Item("Phenylacetic acid"), Item("Phenylbutazone"), Item("Phenylbutyric acid"), Item("Phenylephrine"), Item("Phenylpropanolamine"), Item("Phenytoin"), Item("Pholcodine"), Item("Phylloquinone"), Item("Physostigmine"), Item("Pibrentasvir"), Item("Picosulfuric acid"), Item("Pilocarpine"), Item("Pimavanserin"), Item("Pimecrolimus"), Item("Pimozide"), Item("Pinacidil"), Item("Pinaverium"), Item("Pindolol"), Item("Pioglitazone"), Item("Pipamperone"), Item("Pipazethate"), Item("Pipecuronium"), Item("Piperacillin"), Item("Piperaquine"), Item("Piperazine"), Item("Pipobroman"), Item("Pipotiazine"), Item("Piracetam"), Item("Pirbuterol"), Item("Pirenzepine"), Item("Piretanide"), Item("Pirfenidone"), Item("Piritramide"), Item("Pirlindole"), Item("Piroxicam"), Item("Pitavastatin"), Item("Pitolisant"), Item("Pivampicillin"), Item("Pivmecillinam"), Item("Pixantrone"), Item("Pizotifen"), Item("Plazomicin"), Item("Plicamycin"), Item("Podofilox"), Item("Podophyllin"), Item("Polaprezinc"), Item("Polymyxin B Sulfate"), Item("Polythiazide"), Item("Pomalidomide"), Item("Ponatinib"), Item("Posaconazole"), Item("Potassium"), Item("Potassium alum"), Item("Potassium bicarbonate"), Item("Potassium Chloride"), Item("Potassium Citrate"), Item("Potassium Iodide"), Item("Practolol"), Item("Pralatrexate"), Item("Pramipexole"), Item("Pramlintide"), Item("Pramocaine"), Item("Pranlukast"), Item("Prasterone"), Item("Prasugrel"), Item("Pravastatin"), Item("Prazepam"), Item("Praziquantel"), Item("Prazosin"), Item("Prednicarbate"), Item("Prednisolone"), Item("Prednisone"), Item("Pregabalin"), Item("Pregnenolone"), Item("Prilocaine"), Item("Primaquine"), Item("Primidone"), Item("Probenecid"), Item("Probucol"), Item("Procainamide"), Item("Procaine"), Item("Procaine benzylpenicillin"), Item("Procarbazine"), Item("Procaterol"), Item("Prochlorperazine"), Item("Procyclidine"), Item("Progabide"), Item("Progesterone"), Item("Proguanil"), Item("Promazine"), Item("Promethazine"), Item("Propacetamol"), Item("Propafenone"), Item("Propantheline"), Item("Proparacaine"), Item("Propericiazine"), Item("Propiomazine"), Item("Propiverine"), Item("Propofol"), Item("Propoxycaine"), Item("Propranolol"), Item("Propylthiouracil"), Item("Protionamide"), Item("Protocatechualdehyde"), Item("Protokylol"), Item("Protriptyline"), Item("Prucalopride"), Item("Pseudoephedrine"), Item("Pyrantel"), Item("Pyrazinamide"), Item("Pyridostigmine"), Item("Pyridoxine"), Item("Pyrimethamine"), Item("Pyrvinium"), Item("Quazepam"), Item("Quetiapine"), Item("Quinacrine"), Item("Quinagolide"), Item("Quinapril"), Item("Quinestrol"), Item("Quinethazone"), Item("Quinidine"), Item("Quinine"), Item("Quinupristin"), Item("Rabeprazole"), Item("Radium Ra 223 Dichloride"), Item("Raloxifene"), Item("Raltegravir"), Item("Raltitrexed"), Item("Ramelteon"), Item("Ramipril"), Item("Ramosetron"), Item("Ranitidine"), Item("Ranolazine"), Item("Rapacuronium"), Item("Rasagiline"), Item("Reboxetine"), Item("Regadenoson"), Item("Regorafenib"), Item("Remifentanil"), Item("Remikiren"), Item("Remoxipride"), Item("Repaglinide"), Item("Reposal"), Item("Rescinnamine"), Item("Reserpine"), Item("Resveratrol"), Item("Retapamulin"), Item("Ribavirin"), Item("Ribociclib"), Item("Riboflavin"), Item("Ribostamycin"), Item("Ridogrel"), Item("Rifabutin"), Item("Rifampicin"), Item("Rifapentine"), Item("Rifaximin"),
            Item("Rilmenidine"), Item("Rilpivirine"), Item("Riluzole"), Item("Rimexolone"), Item("Rimonabant"), Item("Riociguat"), Item("Risedronate"), Item("Risedronate"), Item("Risperidone"), Item("Ritodrine"), Item("Ritonavir"), Item("Rivaroxaban"), Item("Rivastigmine"), Item("Rizatriptan"), Item("Rocuronium"), Item("Rofecoxib"), Item("Roflumilast"), Item("Rolapitant"), Item("Rolitetracycline"), Item("Romidepsin"), Item("Ropinirole"), Item("Ropivacaine"), Item("Rosiglitazone"), Item("Rosoxacin"), Item("Rosuvastatin"), Item("Rotigotine"), Item("Roxatidine acetate"), Item("Roxithromycin"), Item("Rucaparib"), Item("Rufinamide"), Item("Rupatadine"), Item("Rutin"), Item("Ruxolitinib"), Item("Sacubitril"), Item("Safinamide"), Item("Salbutamol"), Item("Salicylamide"), Item("Salicylic acid"), Item("Salmeterol"), Item("Salsalate"), Item("Sapropterin"), Item("Saquinavir"), Item("Saxagliptin"), Item("Scopolamine"), Item("Secobarbital"), Item("Selegiline"), Item("Selenium"), Item("Selexipag"), Item("Semaglutide"), Item("Seratrodast"), Item("Sertaconazole"), Item("Sertindole"), Item("Sertraline"), Item("Sevelamer"), Item("Sevoflurane"), Item("Sibutramine"), Item("Sildenafil"), Item("Silodosin"), Item("Silver sulfadiazine"), Item("Simeprevir"), Item("Simvastatin"), Item("Sirolimus"), Item("Sitagliptin"), Item("Sitaxentan"), Item("Sodium aurothiomalate"), Item("Sodium bicarbonate"), Item("Sodium Chloride"), Item("Sodium Citrate"), Item("Sodium feredetate"), Item("Sodium glycerophosphate"), Item("Sodium oxybate"), Item("Sodium phosphate"), Item("Sodium stibogluconate"), Item("Sofosbuvir"), Item("Solifenacin"), Item("Somatostatin"), Item("Sonidegib"), Item("Sorafenib"), Item("Sotalol"), Item("Sparfloxacin"), Item("Spiramycin"), Item("Spirapril"), Item("Spironolactone"), Item("Stanozolol"), Item("Stavudine"), Item("Stepronin"), Item("Stiripentol"),
            Item("Streptomycin"), Item("Streptozocin"), Item("Strontium ranelate"), Item("Succimer"), Item("Succinylcholine"), Item("Sucralfate"), Item("Sufentanil"), Item("Sugammadex"), Item("Sulbactam"), Item("Sulconazole"), Item("Sulfacytine"), Item("Sulfadiazine"), Item("Sulfadimethoxine"), Item("Sulfadoxine"), Item("Sulfamerazine"), Item("Sulfameter"), Item("Sulfamethazine"), Item("Sulfamethizole"), Item("Sulfamethoxazole"), Item("Sulfametopyrazine"), Item("Sulfamoxole"), Item("Sulfanilamide"), Item("Sulfaphenazole"), Item("Sulfapyridine"), Item("Sulfasalazine"), Item("Sulfathiazole"), Item("Sulfinpyrazone"), Item("Sulfisoxazole"), Item("Sulindac"), Item("Sulpiride"), Item("Sultamicillin"), Item("Sumatriptan"), Item("Sunitinib"), Item("Suprofen"), Item("Suramin"), Item("Suvorexant"), Item("Tacrine"), Item("Tacrolimus"), Item("Tadalafil"), Item("Tafenoquine"), Item("Tafluprost"), Item("Talbutal"), Item("Talniflumate"), Item("Tamoxifen"), Item("Tamsulosin"), Item("Tapentadol"), Item("Tartaric acid"), Item("Tasimelteon"), Item("Tasosartan"), Item("Taurolidine"), Item("Tavaborole"), Item("Tazarotene"), Item("Technetium Tc-99m mebrofenin"), Item("Technetium Tc-99m medronate"), Item("Technetium Tc-99m oxidronate"), Item("Technetium Tc-99m sestamibi"), Item("Tedizolid Phosphate"),Item("Tegafur"), Item("Tegafur-uracil"), Item("Tegaserod"), Item("Telaprevir"), Item("Telavancin"),Item("Telithromycin"), Item("Telmisartan"), Item("Temazepam"), Item("Temocillin"), Item("Temoporfin"),Item("Temozolomide"), Item("Temsirolimus"), Item("Teniposide"), Item("Tenofovir disoproxil"), Item("Tenoxicam"),Item("Terazosin"), Item("Terbinafine"), Item("Terbutaline"), Item("Terconazole"), Item("Terfenadine"),Item("Teriflunomide"), Item("Terlipressin"), Item("Tesamorelin"), Item("Testolactone"), Item("Testosterone"),Item("Testosterone cypionate"), Item("Testosterone enanthate"), Item("Testosterone propionate"),Item("Testosterone undecanoate"), Item("Tetrabenazine"), Item("Tetracaine"), Item("Tetracosactide"),Item("Tetracycline"), Item("Tetryzoline"), Item("Tezacaftor"), Item("Thalidomide"), Item("Theobromine"),Item("Theophylline"), Item("Thiabendazole"), Item("Thiamylal"), Item("Thiocolchicoside"), Item("Thiopental"),Item("Thioproperazine"), Item("Thioridazine"), Item("Thiosulfuric acid"), Item("Thiotepa"), Item("Thiothixene"),Item("Thonzylamine"), Item("Thymol"), Item("Tiagabine"), Item("Tianeptine"), Item("Tiapride"),Item("Tiaprofenic acid"), Item("Tibolone"), Item("Ticagrelor"), Item("Ticarcillin"), Item("Ticlopidine"),Item("Tigecycline"), Item("Tiludronic acid"), Item("Timolol"), Item("Tinidazole"), Item("Tioconazole"),Item("Tioguanine"), Item("Tiotropium"), Item("Tipiracil"), Item("Tipranavir"), Item("Tirofiban"),Item("Titanium dioxide"), Item("Tixocortol"), Item("Tizanidine"), Item("Tobramycin"), Item("Tofacitinib"),Item("Tofisopam"), Item("Tolazamide"), Item("Tolazoline"), Item("Tolbutamide"), Item("Tolcapone"),Item("Tolfenamic Acid"), Item("Tolmetin"), Item("Tolnaftate"), Item("Toloxatone"), Item("Tolperisone"),Item("Tolterodine"), Item("Tolvaptan"), Item("Topiramate"), Item("Topiroxostat"), Item("Topotecan"),Item("Torasemide"), Item("Toremifene"), Item("Trabectedin"), Item("Tramadol"), Item("Trametinib"),Item("Trandolapril"), Item("Tranexamic Acid"), Item("Tranilast"), Item("Tranylcypromine"), Item("Trapidil"),Item("Travoprost"), Item("Trazodone"), Item("Treprostinil"), Item("Tretinoin"), Item("Triamcinolone"),Item("Triamterene"), Item("Triazolam"), Item("Trichlormethiazide"), Item("Trichloroethylene"),Item("Triethylenetetramine"), Item("Trifluoperazine"), Item("Triflupromazine"), Item("Trifluridine"),Item("Triflusal"), Item("Trihexyphenidyl"), Item("Trilostane"), Item("Trimebutine"), Item("Trimetazidine"),Item("Trimethadione"), Item("Trimethaphan"), Item("Trimethoprim"), Item("Trimetrexate"), Item("Trimipramine"),Item("Trioxsalen"), Item("Tripelennamine"), Item("Triprolidine"), Item("Triptorelin"), Item("Tritoqualine"),Item("Trolamine salicylate"), Item("Troleandomycin"), Item("Tromethamine"), Item("Tropicamide"),Item("Tropisetron"), Item("Trospium"), Item("Trovafloxacin"), Item("Tubocurarine"), Item("Ubidecarenone"),Item("Udenafil"), Item("Ulipristal"), Item("Ulobetasol"), Item("Umeclidinium"), Item("Unoprostone"),Item("Uracil mustard"), Item("Ursodeoxycholic acid"), Item("Valaciclovir"), Item("Valbenazine"), Item("Valdecoxib"),Item("Valganciclovir"), Item("Valproic Acid"), Item("Valrubicin"), Item("Valsartan"), Item("Vancomycin"),Item("Vandetanib"), Item("Vapreotide"), Item("Vardenafil"), Item("Varenicline"), Item("Vecuronium"),Item("Velpatasvir"), Item("Vemurafenib"), Item("Venetoclax"), Item("Venlafaxine"), Item("Verapamil"),Item("Vernakalant"), Item("Verteporfin"), Item("Vigabatrin"), Item("Vilanterol"), Item("Vilazodone"),Item("Vildagliptin"), Item("Viloxazine"), Item("Vinblastine"), Item("Vincristine"), Item("Vindesine"),Item("Vinflunine"), Item("Vinorelbine"), Item("Vismodegib"), Item("Vitamin A"), Item("Vitamin C"),Item("Vitamin E"), Item("Voacamine"), Item("Voglibose"), Item("Vorapaxar"), Item("Voriconazole"),Item("Vorinostat"), Item("Vortioxetine"), Item("Voxilaprevir"), Item("Warfarin"), Item("Xanthinol"),Item("Ximelagatran"), Item("Xylometazoline"), Item("Yohimbine"), Item("Zafirlukast"), Item("Zalcitabine"), Item("Zaleplon"), Item("Zaltoprofen"), Item("Zidovudine"), Item("Zileuton"), Item("Zinc oxide"), Item("Zinc picolinate"), Item("Zinc sulfate"), Item("Ziprasidone"), Item("Zoledronic acid"), Item("Zolmitriptan"), Item("Zolpidem"), Item("Zonisamide"), Item("Zopiclone"), Item("Zotepine"), Item("Zucapsaicin"), Item("Zuclopenthixol"),
        )





        adapter = MyAdapter(requireContext(), android.R.layout.simple_list_item_1, items)
        listView.adapter = adapter
        listView.visibility = View.GONE


        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrBlank()) {
                    adapter.filterOriginalItems()
                    listView.visibility = View.GONE
                } else {
                    adapter.filter(newText)
                    listView.visibility = View.VISIBLE
                }
                return true
            }
        })

        val checkInteractionsBtn = view.findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = view.findViewById<Button>(R.id.btnClear)
        val loadExampleBtn = view.findViewById<Button>(R.id.btnLoadExample)

        checkInteractionsBtn.isEnabled = false
        clearBtn.isEnabled = false

        listView.setOnItemClickListener { _, _, position, _ ->
            val selectedItemName = adapter?.getItem(position)?.name
            if (!selectedItemName.isNullOrBlank() && buttonCount < 2) {
                createButton(selectedItemName)
                searchView.setQuery("", false)
                clearBtn.isEnabled = true
            }
        }


        loadExampleBtn?.setOnClickListener {
            loadExampleButtons()
        }

        clearBtn?.setOnClickListener {
            if (buttonCount < 2) {
                clearButtons()
                clearBtn.isEnabled = false
            }
            checkInteractionsBtn.isEnabled = false
        }

        checkInteractionsBtn?.setOnClickListener {
            checkInteractions()
        }

        return view
    } /////////////////// END OF ONCREATE =================


    /////////////////// START OF MY FUNCTIONS =================

    private fun createButton(buttonText: String) {
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)


        if (buttonCount < 2) {
            val newButton = Button(requireContext())
            newButton.text = buttonText

            val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
            val itemView = LayoutInflater.from(context).inflate(R.layout.item_drugdrug, buttonContainer, false)
            val txtItemDrug = itemView.findViewById<TextView>(R.id.txtItemDrug)
            txtItemDrug.text = buttonText

            buttonContainer.addView(itemView)
            createdButtons.add(newButton)
            buttonCount++

            itemView.setOnClickListener {
                removeButton(itemView)
            }
        }

        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)

        if (buttonCount == 2) {
            txtAddtwodrugs?.setText(R.string.lbl_added_two_drugs)
            txtAddtwodrugs?.setTypeface(null, Typeface.BOLD)

            checkInteractionsBtn.isEnabled = true

            val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_teal_100_radius_21_5)
            checkInteractionsBtn.background = checkInteractionsDrawable
            checkInteractionsBtn.setTextAppearance(requireContext(), R.style.whitetext)

            checkInteractionsBtn?.setOnClickListener {
                checkInteractions()
            }
        }

        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_white_a700_border_teal_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.textBlue)
        clearBtn?.setOnClickListener {
            buttonCount == 0
            clearButtons()
        }
    }


    private fun removeButton(view: View) {
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)

        buttonContainer?.removeView(view)
        createdButtons.remove(view)
        buttonCount--

        txtAddtwodrugs?.setText(R.string.lbl_add_two_drugs)
        txtAddtwodrugs?.setTypeface(null, Typeface.NORMAL)


        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_white_a700_border_teal_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.textBlue)


        if (buttonCount == 1){
            val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
            checkInteractionsBtn.background = checkInteractionsDrawable
            checkInteractionsBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)
        }

        if(buttonCount == 0){
            val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
            clearBtn.background = clearBtnDrawable
            clearBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)
        }

        checkInteractionsBtn?.isEnabled = false
        clearBtn?.isEnabled = true
    }





    fun readCSVFromAssets(context: Context, filename: String): List<DrugList> {
        val csvData: MutableList<DrugList> = mutableListOf()

        try {
            val inputStream = context.assets.open(filename)
            val reader = BufferedReader(InputStreamReader(inputStream))
            var line: String?

            // Assuming the first line contains headers and should be skipped
            reader.readLine()

            while (reader.readLine().also { line = it } != null) {
                val values = line!!.split(",") // Assuming the CSV is comma-separated
                if (values.size >= 2) {
                    val item = DrugList(0,values[0], values[1])
                    csvData.add(item)
                }
            }

            reader.close()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return csvData
    }



    private fun loadExampleButtons() {
        val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)
        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)


        buttonCount = 2
        if (buttonCount == 2) {
            txtAddtwodrugs?.text = getString(R.string.lbl_added_two_drugs)
            txtAddtwodrugs?.setTypeface(null, Typeface.BOLD)

            val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_teal_100_radius_21_5)
            checkInteractionsBtn.background = checkInteractionsDrawable
            checkInteractionsBtn.setTextAppearance(requireContext(), R.style.whitetext)
        }


        clearButtons()
        val random = Random()
        val startIndex = random.nextInt(100) * 200
        for (i in 0 until 2) {
            val randomIndex = startIndex + (i * 200)
            createButton(items[randomIndex % items.size].name)
        }

        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_white_a700_border_teal_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.textBlue)

        checkInteractionsBtn.isEnabled = true
        clearBtn.isEnabled = true
    }

    fun clearButtons() {
        val buttonContainer = requireView().findViewById<LinearLayout>(R.id.buttonContainer)
        val checkInteractionsBtn = requireView().findViewById<Button>(R.id.btnCheckInteractions)
        val clearBtn = requireView().findViewById<Button>(R.id.btnClear)

        buttonContainer.removeAllViews()
        createdButtons.clear()


        val clearBtnDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
        clearBtn.background = clearBtnDrawable
        clearBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)

        val checkInteractionsDrawable: Drawable? = ContextCompat.getDrawable(requireContext(), R.drawable.rectangle_bg_bluegray_100_radius_21_5)
        checkInteractionsBtn.background = checkInteractionsDrawable
        checkInteractionsBtn.setTextAppearance(requireContext(), R.style.txtWhiteGrayButton)

        buttonCount = 0
        clearBtn?.isEnabled = false

        val txtAddtwodrugs = requireView().findViewById<TextView>(R.id.txtAddtwodrugs)
        txtAddtwodrugs?.setText(R.string.lbl_add_two_drugs)
        txtAddtwodrugs?.setTypeface(null, Typeface.NORMAL)


//        val interactionResultText = requireView().findViewById<TextView>(R.id.txtInteractionResult)
//        interactionResultText.visibility = View.GONE
//        val drugdrugResults = requireView().findViewById<LinearLayout>(R.id.drugdrugResults)
//        drugdrugResults.visibility = View.GONE
    }

    fun checkInteractions() {
        val createdButtons = createdButtons.toList()
        if (createdButtons.size == 2) {
            val drug1 = createdButtons[0].text.toString()
            val drug2 = createdButtons[1].text.toString()

//            val drugOne = requireView().findViewById<TextView>(R.id.drugOne)
//            val drugTwo = requireView().findViewById<TextView>(R.id.drugTwo)
//
//            drugOne.text = drug1
//            drugTwo.text = drug2

            // Create an instance of InteractionResultFragment with drug names
            val fragment = InteractionResultFragment.newInstance(drug1, drug2)

            // Navigate to InteractionResultFragment
            val fragmentManager = parentFragmentManager
            val transaction = fragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_interaction_result, fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }
    }




    /////////////////// END OF MY FUNCTIONS =================
    companion object {
        @JvmStatic
        fun newInstance() =
            DrugdrugFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}