from django.db import models


# Create your models here.
class Crawling(models.Model):
    title = models.CharField(max_length=255)
    lprice = models.IntegerField()
    modifyDate = models.DateTimeField(auto_now_add=True)

    def __str__(self):
        return [self.title, self.lprice, self.modifyDate]


class TblMember(models.Model):
    mid = models.CharField(primary_key=True, max_length=255)
    mpw = models.CharField(max_length=255, blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tbl_member'

    def __str__(self):
        return [self.mid, self.mpw]


class TblProduct(models.Model):
    pno = models.AutoField(primary_key=True)
    price = models.IntegerField(blank=True, null=True)
    title = models.CharField(max_length=255, blank=True, null=True)
    member_mid = models.ForeignKey(TblMember, models.DO_NOTHING, db_column='member_mid', blank=True, null=True)

    class Meta:
        managed = False
        db_table = 'tbl_product'

    def __str__(self):
        return [self.pno, self.price, self.title, self.member_mid]
