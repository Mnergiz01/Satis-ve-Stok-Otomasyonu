package tr.com.cafeotomasyon.templatemethod;

import tr.com.cafeotomasyon.entity.UrunlerContract;

public abstract class UrunEklemeTemplate {
	public final void urunEkle(UrunlerContract urun) {
        validateUrun(urun);
        insertUrun(urun);
        afterInsert(urun);
    }

    protected abstract void validateUrun(UrunlerContract urun);
    protected abstract void insertUrun(UrunlerContract urun);
    protected abstract void afterInsert(UrunlerContract urun);

}

